package org.aelion.categories.productToCategory;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductToCategoryService {
    List<ProductToCategory> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> add(String productId, List<Long> categoryIds);
    List<ProductToCategory> getCategoriesIdsByProductEan(String productId);
    ProductToCategory getProductsByCategoryId(Long categoryId);
    List<ProductToCategory> getRelatedCategories(Long categoryId);

}
