package org.aelion.categories.categoryToCommunity;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryToCommunityService {
    List<CategoryToCommunity> getAll();


    ResponseEntity<?> getCategoriesByCommunityId(String communityId);


    ResponseEntity<?> add(CategoryToCommunity catToCom);
}
