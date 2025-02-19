package org.aelion.categories.categoryToCommunity;

import org.aelion.categories.productToCategory.ProductToCategory;

import java.util.List;

public interface CategoryToCommunityService {
    List<CategoryToCommunity> getAll();


    List<CategoryToCommunity> getCategoriesByCommunityId(String communityId);

    CategoryToCommunity getByCommunityIdAndCategoryId(String communityId,Long categoryId);

    void UpdatePreferenciesFactors(String communityId , Long qte , List<ProductToCategory> categories);

    void add(CategoryToCommunity catToCom);
}

