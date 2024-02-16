package org.aelion.categories.productToCategory;

import org.aelion.categories.productToCategory.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories/products")
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping("/{productEan}")
    public ResponseEntity<?> getCategoriesIdsByProductId(@PathVariable String productEan) {
        return service.getCategoriesIdsByProductEan(productEan);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getRandomProductByCategory(@PathVariable Long categoryId) {
        return service.getProductsByCategoryId(categoryId);
    }
    @GetMapping("/related/{categoryId}")
    public  ResponseEntity<?> getRelatedCategories(@PathVariable Long categoryId){
        return  service.getRelatedCategories(categoryId);
    }
    @PostMapping("/{productEan}")
    public ResponseEntity<?> add(@PathVariable String productEan, @RequestBody CategoriesDto dto) {
        return service.add(productEan, dto.getCategoriesIds());
    }

}
