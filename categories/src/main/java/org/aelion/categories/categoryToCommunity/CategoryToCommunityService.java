package org.aelion.categories.categoryToCommunity;

import org.aelion.categories.categories.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryToCommunityService {
    List<CategoryToCommunity> getAll();


    List<CategoryToCommunity> getCategoriesByCommunityId(String communityId);

    ResponseEntity<?> getByCommunityIdAndCategoryId(String communityId,String categoryId);

    ResponseEntity<?> UpdatePreferenciesFactors(String communityId , Long qte , List<Category> categories);

    ResponseEntity<?> add(CategoryToCommunity catToCom);
}
