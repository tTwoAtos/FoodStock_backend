package org.aelion.categories.categoryToCommunity;

import org.aelion.categories.productToCategory.ProductToCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories/community", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public CategoryToCommunity getByCommunityIdAndCategoryId(
            @PathVariable String communityId,
            @PathVariable Long categoryId) {
                return service.getByCommunityIdAndCategoryId(communityId, categoryId);
    }


    @PostMapping("/{communityId}/{qte}")
    public void updatePreferenciesFactors(
            @PathVariable String communityId,
            @PathVariable Long qte,
            @RequestBody List<ProductToCategory> categories) {
                service.UpdatePreferenciesFactors(communityId, qte, categories);
    }


    @PostMapping
    public void add(@RequestBody CategoryToCommunity catToCom) {
        service.add(catToCom);
    }

}
