package org.aelion.categories.categoryToCommunity;

import org.aelion.categories.categories.Category;
import org.aelion.categories.productToCategory.ProductToCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories/community")
public class CategoryToCommunityController {
    @Autowired
    private CategoryToCommunityService service;

    @GetMapping
    public List<CategoryToCommunity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{communityId}")
    public List<CategoryToCommunity> getCategoriesByCommunityId(@PathVariable String communityId) {

        return service.getCategoriesByCommunityId(communityId);
    }

    @GetMapping("/{communityId}/{categoryId}")
    public ResponseEntity<?> getByCommunityIdAndCategoryId(@PathVariable String communityId, @PathVariable Long categoryId) {

        return service.getByCommunityIdAndCategoryId(communityId,categoryId);
    }

    @PostMapping("/{communityId}/{qte}")
    public ResponseEntity<?> UpdatePreferenciesFactors(@PathVariable String communityId,@PathVariable Long qte, @RequestBody List<ProductToCategory> categories) {
        System.out.println(categories.get(0).getProductId());
        return service.UpdatePreferenciesFactors(communityId ,qte ,categories);
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable CategoryToCommunity catToCom) {
        return service.add(catToCom);
    }

}
