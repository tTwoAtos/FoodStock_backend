package org.aelion.categories.categories.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categories.CategoryRepository;
import org.aelion.categories.categories.CategoryService;
import org.aelion.categories.productToCategory.Impl.ProductToCategoryServiceImpl;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
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
    public Category getById(String code) {
        return repository.findById(code).orElseThrow(() -> new NotFoundException("Category not found"));
    }


    @Override
    public void add(List<String> categories, String productCode) {
        List<Category> tmpCategories = new ArrayList<>();

        for (String cat : categories) {
            Optional<Category> existingCat = repository.findByName(cat);
            Category tmpCat = existingCat.orElseGet(() -> {
                Category newCat = new Category();
                newCat.setName(cat);
                return newCat;
            });

            tmpCategories.add(tmpCat);
        }

        List<Category> savedCategories = repository.saveAll(tmpCategories);
        List<Long> categoriesIds = savedCategories.stream().map(Category::getId).toList();

        productToCategoryService.add(productCode, categoriesIds);

        if (savedCategories.isEmpty()) {
            throw new NotFoundException("Categories not found or could not be saved.");
        }
    }

}
