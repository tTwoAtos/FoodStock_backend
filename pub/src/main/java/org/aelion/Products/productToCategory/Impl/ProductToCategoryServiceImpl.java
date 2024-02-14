package org.aelion.Products.productToCategory.Impl;

import org.aelion.Products.categories.CategoryRepository;
import org.aelion.Products.productToCategory.ProductToCategory;
import org.aelion.Products.productToCategory.ProductToCategoryRepository;
import org.aelion.Products.productToCategory.ProductToCategoryService;
import org.aelion.Products.productToCategory.dto.ProductToCategoryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final String PRODUCT_URL_API = "http://PRODUCT-SERVICE/api/v1/products"


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

    }
}
