package org.aelion.Products.categoryToCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoryToCommunity")
public class CategoryToCommunityController {
    @Autowired
    private CategoryToCommunityService service;

    @GetMapping
    public List<CategoryToCommunity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{communityId}")
    public List<CategoryToCommunity> getCategorieByCommunityId(@PathVariable String communityId) {
        return service.getCategorieByCommunityId(communityId);
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable CategoryToCommunity catToCom) {
        return service.add(catToCom);
    }

}
