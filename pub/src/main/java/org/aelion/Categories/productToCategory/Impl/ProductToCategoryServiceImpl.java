package org.aelion.Categories.productToCategory.Impl;

import org.aelion.Categories.categories.CategoryRepository;
import org.aelion.Categories.productToCategory.ProductToCategory;
import org.aelion.Categories.productToCategory.ProductToCategoryRepository;
import org.aelion.Categories.productToCategory.ProductToCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductToCategoryServiceImpl implements ProductToCategoryService {
    @Autowired
    private ProductToCategoryRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_GATEWAY}")
    private String API_GATEWAY_URL;

    private final String PRODUCT_URL_API = "http://PRODUCT-SERVICE/api/v1/products";


    @Override
    public List<ProductToCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(String productEan, List<String> categoriesIds) {

        ProductToCategory pdc = new ProductToCategory();
        pdc.setProductId(productEan);
        pdc.setCategoryIds(categoriesIds);

        repository.save(pdc);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
