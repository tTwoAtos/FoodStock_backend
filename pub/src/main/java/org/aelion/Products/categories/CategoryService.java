package org.aelion.Products.categories;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> add(List<Category> categories, String productEan);
}
