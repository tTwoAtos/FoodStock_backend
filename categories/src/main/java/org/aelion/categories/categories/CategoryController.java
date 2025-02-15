package org.aelion.categories.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Category> categories = service.getAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des catégories : " + e.getMessage());
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> get(@PathVariable String categoryId) {
        try {
            return service.getById(categoryId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Catégorie non trouvée avec l'ID : " + categoryId);
        }
    }

    @PostMapping("{productCode}")
    public ResponseEntity<?> add(@RequestBody List<String> categories, @PathVariable String productCode) {
        try {
            return service.add(categories, productCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout des catégories au produit " + productCode + " : " + e.getMessage());
        }
    }
}
