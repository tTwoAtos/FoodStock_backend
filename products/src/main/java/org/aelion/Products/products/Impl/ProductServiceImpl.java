package org.aelion.Products.products.Impl;

import org.aelion.Products.products.Product;
import org.aelion.Products.products.ProductRepository;
import org.aelion.Products.products.ProductService;
import org.aelion.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    @Override
    public List<Product> getAll(Pageable pageable, Map<String, String> filters) {
        return List.of();
    }


    @Override
    public List<Product> getAllByIdList(List<String> productIds) {
        return repository.findByIdList(productIds);
    }

    @Override
    public Product getById(String code) {
        Optional<Product> optionalProduct = repository.findById(code);

        if (optionalProduct.isPresent()) {
            Product product = new Product(
                    optionalProduct.get().getEANCode(),
                    optionalProduct.get().getName(),
                    optionalProduct.get().getNbScanned() + 1,
                    optionalProduct.get().getNbAdded(),
                    optionalProduct.get().getThumbnail()
            );
            return repository.save(product);
        }

        else {
            Product product = getFromOpenFoodFact(code);

            if (product != null) {
                return product;  // Retourner le produit récupéré depuis OpenFoodFact
            } else {
                throw new NotFoundException("Produit non trouvé avec le code : " + code);
            }
        }
    }


    @Override
    public void addedToCommunity(String code) {
        Product product = repository.findById(code)
                .orElseThrow(() -> new NotFoundException("Product not found with code " + code));
        product.setNbAdded(product.getNbAdded() + 1);
        repository.save(product);
    }

    private Product getFromOpenFoodFact(String code) {
        RestTemplate tmpRestTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = tmpRestTemplate.exchange(
                foodFactApi + '/' + code,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {});

        if (!response.hasBody()) {
            return null;
        }

        Map<String, Object> body = response.getBody();
        String name = (String) ((Map<String, Object>) body.get("product")).get("generic_name_fr");
        if (name.isEmpty()) {
            name = (String) ((Map<String, Object>) body.get("product")).get("product_name_fr");
        }

        String thumbnail = (String) ((Map<String, Object>) body.get("product")).get("image_thumb_url");
        List<String> categories = (List<String>) ((Map<List<String>, Object>) body.get("product")).get("categories_tags");


        categories.replaceAll(k -> k.split(":")[1]);

        Product product = new Product(
                code,
                name,
                0L,
                0L,
                thumbnail
        );

        product = repository.save(product);

        restTemplate.postForObject("http://CATEGORY-SERVICE/api/v1/categories/" + product.getEANCode(), categories, List.class);
        return product;
    }
}
