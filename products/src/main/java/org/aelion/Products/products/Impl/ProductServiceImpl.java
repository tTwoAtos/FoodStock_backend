package org.aelion.Products.products.Impl;

import org.aelion.Products.products.Product;
import org.aelion.Products.products.ProductRepository;
import org.aelion.Products.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        Optional<Product> optionalProduct = repository.findById(code);

        if (optionalProduct.isPresent()) {
            Product product = repository.save(
                    new Product(
                            optionalProduct.get().getEANCode(),
                            optionalProduct.get().getName(),
                            optionalProduct.get().getNbScanned() + 1,
                            optionalProduct.get().getNbAdded())
            );
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            Product product = getFromOpenFoodFact(code);

            if (product != null)
                return new ResponseEntity<>(product, HttpStatus.OK);
            else
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> addedToCommunity(String code) {
        Product product = repository.findById(code).orElseThrow();
        product.setNbAdded(product.getNbAdded() + 1);
        repository.save(product);
        
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private Product getFromOpenFoodFact(String code) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(foodFactApi + '/' + code, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {
        });

        if (!response.hasBody()) {
            return null;
        }

        Map<String, Object> body = response.getBody();

        String name = (String) ((Map<String, Object>) body.get("product")).get("generic_name");

        return repository.save(new Product(
                code,
                name,
                0L, 0L)
        );
    }
}
