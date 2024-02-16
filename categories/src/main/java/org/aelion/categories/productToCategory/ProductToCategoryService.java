package org.aelion.categories.productToCategory;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductToCategoryService {
    List<ProductToCategory> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> add(String productId, List<Long> categoryIds);
    ResponseEntity<?> getCategoriesIdsByProductEan(String productId);
    ResponseEntity<?> getProductsByCategoryId(Long categoryId);
    ResponseEntity<?> getRelatedCategories(Long categoryId);

}
