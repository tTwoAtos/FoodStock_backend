package org.aelion.Products.productToCategory.Impl;

import org.aelion.Products.productToCategory.ProductToCategory;
import org.aelion.Products.productToCategory.ProductToCategoryRepository;
import org.aelion.Products.productToCategory.ProductToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductToProductServiceImpl implements ProductToCategoryService {
    @Autowired
    private ProductToCategoryRepository repository;

    @Override
    public List<ProductToCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(ProductToCategory prodToCom) {
        return null;
    }
}
