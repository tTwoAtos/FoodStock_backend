package org.aelion.categories.categories.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categories.CategoryRepository;
import org.aelion.categories.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public ResponseEntity<?> add(List<String> categories, String productCode) {
        List<Category> tmpCategories = new ArrayList<>();

        for (String cat : categories){
            Category tmpCat = new Category();
            tmpCat.setName(cat);
            tmpCategories.add(tmpCat);
        }

        System.out.println(tmpCategories);
        List<Category> resp = repository.saveAll(tmpCategories);

        if (resp.isEmpty())
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
