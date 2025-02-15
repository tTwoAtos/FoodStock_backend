package org.aelion.categories.categoryToCommunity;

import org.aelion.categories.productToCategory.ProductToCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories/community", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryToCommunityController {
    @Autowired
    private CategoryToCommunityService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CategoryToCommunity> categories = service.getAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des catégories associées aux communautés : " + e.getMessage());
        }
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<?> getCategoriesByCommunityId(@PathVariable String communityId) {
        try {
            List<CategoryToCommunity> categories = service.getCategoriesByCommunityId(communityId);
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucune catégorie trouvée pour la communauté avec l'ID : " + communityId);
        }
    }

    @GetMapping("/{communityId}/{categoryId}")
    public ResponseEntity<?> getByCommunityIdAndCategoryId(@PathVariable String communityId, @PathVariable Long categoryId) {
        try {
            return service.getByCommunityIdAndCategoryId(communityId, categoryId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucune correspondance trouvée pour la communauté " + communityId + " et la catégorie " + categoryId);
        }
    }

    @PostMapping("/{communityId}/{qte}")
    public ResponseEntity<?> updatePreferenciesFactors(@PathVariable String communityId, @PathVariable Long qte, @RequestBody List<ProductToCategory> categories) {
        try {
            System.out.println(categories.get(0).getProductId());
            return service.UpdatePreferenciesFactors(communityId, qte, categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la mise à jour des préférences pour la communauté " + communityId + " : " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryToCommunity catToCom) {
        try {
            return service.add(catToCom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de la catégorie à la communauté : " + e.getMessage());
        }
    }
}
