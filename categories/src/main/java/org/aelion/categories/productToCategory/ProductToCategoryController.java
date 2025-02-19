package org.aelion.categories.productToCategory;

import org.aelion.categories.productToCategory.dto.CategoriesDto;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping("/{productEan}")
    public List<Long> getCategoriesIdsByProductId(@PathVariable String productEan) {
        List<ProductToCategory> productToCategories = service.getCategoriesIdsByProductEan(productEan);

        if (productToCategories == null || productToCategories.isEmpty()) {
            throw new NotFoundException("Aucune catégorie trouvée pour ce produit.");
        }

        return productToCategories.stream()
                .map(ProductToCategory::getCategoryId)
                .toList();
    }



    @GetMapping("/category/{categoryId}")
    public ProductToCategory getRandomProductByCategory(@PathVariable Long categoryId) {
        ProductToCategory product = service.getProductsByCategoryId(categoryId);

        if (product == null) {
            throw new NotFoundException("Aucun produit trouvé pour cette catégorie.");
        }
        return product;
    }



    @GetMapping("/related/{categoryId}")
    public List<ProductToCategory> getRelatedCategories(@PathVariable Long categoryId) {
        List<ProductToCategory> productToCategories = service.getRelatedCategories(categoryId);

        if (productToCategories.isEmpty()) {
            throw new NotFoundException("Aucune catégorie associée trouvée pour cet identifiant.");
        }
        return productToCategories;
    }



    @PostMapping("/{productEan}")
    public ProductToCategory add(@PathVariable String productEan, @RequestBody CategoriesDto dto) {
        return service.add(productEan, dto.getCategoriesIds());
    }

}
