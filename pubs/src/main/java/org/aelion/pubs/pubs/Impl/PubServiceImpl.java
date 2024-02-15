package org.aelion.pubs.pubs.Impl;

import org.aelion.pubs.pubs.Pub;
import org.aelion.pubs.pubs.PubRepository;
import org.aelion.pubs.pubs.PubService;
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
public class PubServiceImpl implements PubService {
    @Autowired
    private PubRepository repository;
    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    @Override
    public List<Pub> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        Optional<Pub> optionalProduct = repository.findById(code);

        if (optionalProduct.isPresent()) {
            Pub product = repository.save(
                    new Pub(
                            optionalProduct.get().getEANCode(),
                            optionalProduct.get().getName(),
                            optionalProduct.get().getNbScanned() + 1,
                            optionalProduct.get().getNbAdded(),
                            optionalProduct.get().getThumbnail()
                    )
            );

            return new ResponseEntity<>(product, HttpStatus.OK);

        } else {
            Pub product = getFromOpenFoodFact(code);

            if (product != null)
                return new ResponseEntity<>(product, HttpStatus.OK);
            else
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> addedToCommunity(String code) {
        Pub product = repository.findById(code).orElseThrow();
        product.setNbAdded(product.getNbAdded() + 1);
        repository.save(product);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private Pub getFromOpenFoodFact(String code) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(foodFactApi + '/' + code, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {
        });

        if (!response.hasBody()) {
            return null;
        }

        Map<String, Object> body = response.getBody();

        String name = (String) ((Map<String, Object>) body.get("product")).get("generic_name");
        String thumbnail = (String) ((Map<String, Object>) body.get("product")).get("image_thumb_url");
        List<String> categories = (List<String>) ((Map<List<String>, Object>) body.get("product")).get("categories_tags");

        categories.replaceAll((k) -> k.split(":")[1]);
        Pub product = repository.save(
                new Pub(
                        code,
                        name,
                        0L,
                        0L,
                        thumbnail
                )
        );

        restTemplate.postForObject("http://CATEGORY-SERVICE/api/v1/categories/" + product.getEANCode(), categories, ResponseEntity.class);
        return product;
    }
}
