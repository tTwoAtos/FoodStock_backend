package org.aelion.Products.categories.Impl;

import org.aelion.Products.categories.Category;
import org.aelion.Products.categories.CategoryRepository;
import org.aelion.Products.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Value("${API_GATEWAY}")
    String API_GATEWAY;
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(List<Category> categories, String productEan) {
        List<Category> resp = repository.saveAll(categories);

        if (resp.isEmpty())
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = restTemplate.postForObject(API_GATEWAY + "/productToCategory/" + productEan, resp, ResponseEntity.class);

        if (response.getBody() == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
