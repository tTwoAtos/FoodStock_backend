package org.aelion.categories.productToCategory;

import org.aelion.categories.productToCategory.dto.CategoriesDto;
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
    public ResponseEntity<?> getCategoriesIdsByProductId(@PathVariable String productEan) {
        try {
            List<ProductToCategory> productToCategories = service.getCategoriesIdsByProductEan(productEan);

            if (productToCategories == null || productToCategories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucune catégorie trouvée pour le produit avec l'EAN : " + productEan);
            }

            return ResponseEntity.ok(productToCategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des catégories du produit : " + e.getMessage());
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getRandomProductByCategory(@PathVariable Long categoryId) {
        try {
            ProductToCategory product = service.getProductsByCategoryId(categoryId);

            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucun produit trouvé pour la catégorie ID : " + categoryId);
            }

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération du produit par catégorie : " + e.getMessage());
        }
    }

    @GetMapping("/related/{categoryId}")
    public ResponseEntity<?> getRelatedCategories(@PathVariable Long categoryId) {
        try {
            List<ProductToCategory> productToCategories = service.getRelatedCategories(categoryId);

            if (productToCategories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucune catégorie liée trouvée pour la catégorie ID : " + categoryId);
            }

            return ResponseEntity.ok(productToCategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des catégories liées : " + e.getMessage());
        }
    }

    @PostMapping("/{productEan}")
    public ResponseEntity<?> add(@PathVariable String productEan, @RequestBody CategoriesDto dto) {
        try {
            return service.add(productEan, dto.getCategoriesIds());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout des catégories au produit : " + e.getMessage());
        }
    }
}
