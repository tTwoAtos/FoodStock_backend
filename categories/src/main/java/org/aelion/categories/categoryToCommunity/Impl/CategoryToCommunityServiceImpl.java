package org.aelion.categories.categoryToCommunity.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categoryToCommunity.CategoryToCommunity;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityService;
import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryToCommunityServiceImpl implements CategoryToCommunityService {
    @Autowired
    private CategoryToCommunityRepository repository;


    @Override
    public List<CategoryToCommunity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CategoryToCommunity> getCategoriesByCommunityId(String communityId) {

        return repository.findTop5ByCommunityIdOrderByPreferenciesFactorDesc(communityId);
    }

    @Override
    public CategoryToCommunity getByCommunityIdAndCategoryId(String communityId, String categoryId) {
        return null;
    }

    @Override
    public ResponseEntity<?> UpdatePreferenciesFactors(String communityId , Long qte , List<Category> categories) {

        for(Category category : categories){
            String categoryId = category.getId().toString();
            Optional<CategoryToCommunity> optionalCategoryToCommunity= repository.findByCommunityIdAndCategoryId(communityId,categoryId);

            if(optionalCategoryToCommunity.isPresent()){
                CategoryToCommunity categoryToCommunity = repository.save(new CategoryToCommunity(
                        optionalCategoryToCommunity.get().getId(),
                        optionalCategoryToCommunity.get().getCommunityId(),
                        optionalCategoryToCommunity.get().getCategoryId(),
                        optionalCategoryToCommunity.get().getPreferenciesFactor()+qte
                        )
                );
            }else{
                CategoryToCommunity ctoc = new CategoryToCommunity();
                ctoc.setCommunityId(communityId);
                ctoc.setCategoryId(categoryId);
                ctoc.setPreferenciesFactor(qte);
                repository.save(ctoc);
            }
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(CategoryToCommunity catToCom) {
        return null;
    }
}
