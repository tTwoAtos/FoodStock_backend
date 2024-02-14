package org.aelion.Products.categoryToCommunity;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryToCommunityService {
    List<CategoryToCommunity> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> add(CategoryToCommunity catToCom);
}
