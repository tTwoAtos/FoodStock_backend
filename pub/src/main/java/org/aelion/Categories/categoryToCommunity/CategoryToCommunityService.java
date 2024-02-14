package org.aelion.Categories.categoryToCommunity;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryToCommunityService {
    List<CategoryToCommunity> getAll();

    List<CategoryToCommunity> getAllCategoriesByCommunityId(String communityId);


    ResponseEntity<?> add(CategoryToCommunity catToCom);
}
