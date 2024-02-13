package org.aelion.Products.categories.Impl;

import org.aelion.Products.categories.Category;
import org.aelion.Products.categories.CategoryRepository;
import org.aelion.Products.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(List<String> code) {
        return null;
    }

    private Category getFromOpenFoodFact(String code) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(foodFactApi + '/' + code, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {
        });

        if (!response.hasBody()) {
            return null;
        }

        Map<String, Object> body = response.getBody();

        String name = (String) ((Map<String, Object>) body.get("product")).get("generic_name");

//        return repository.save(new Category(
//                code,
//                name,
//                0L, 0L)
//        );
        return null;
    }
}
