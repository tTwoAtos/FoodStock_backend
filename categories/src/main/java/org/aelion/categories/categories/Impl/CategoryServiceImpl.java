package org.aelion.categories.categories.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categories.CategoryRepository;
import org.aelion.categories.categories.CategoryService;
import org.aelion.categories.productToCategory.Impl.ProductToCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Value("${API_GATEWAY}")
    String API_GATEWAY;

    private final String CATEGORY_API = "http://localhost:4009/api/v1/categories";
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ProductToCategoryServiceImpl productToCategoryService;
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

            Optional<Category> existingCat = repository.findByName(cat);
            if (existingCat.isPresent())
                tmpCat.setId(existingCat.get().getId());

            tmpCategories.add(tmpCat);
        }

        List<Category> resp = repository.saveAll(tmpCategories);

        List<Long> categoriesIds = resp.stream().map((item) -> item.getId()).toList();

        ResponseEntity<?> res = productToCategoryService.add(productCode, categoriesIds);

        if (resp.isEmpty() || !res.hasBody())
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
