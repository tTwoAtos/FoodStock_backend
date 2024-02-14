package org.aelion.Products.productToCategory;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductToCategoryService {
    List<ProductToCategory> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> add(String productId, List<String> categoryIds);
}
