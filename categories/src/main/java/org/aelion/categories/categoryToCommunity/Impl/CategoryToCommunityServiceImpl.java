package org.aelion.categories.categoryToCommunity.Impl;

import org.aelion.categories.categoryToCommunity.CategoryToCommunity;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityService;
import org.aelion.categories.productToCategory.ProductToCategory;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        return repository.findTop20ByCommunityIdOrderByPreferenciesFactorDesc(communityId);
    }

    @Override
    public CategoryToCommunity getByCommunityIdAndCategoryId(String communityId, Long categoryId) {
        Optional<CategoryToCommunity> optionalCategoryToCommunity = repository.findByCommunityIdAndCategoryId(communityId, categoryId);

        if (optionalCategoryToCommunity.isPresent()) {
            CategoryToCommunity categoryToCommunity = optionalCategoryToCommunity.get();
            return new CategoryToCommunity(
                    categoryToCommunity.getId(),
                    categoryToCommunity.getCommunityId(),
                    categoryToCommunity.getCategoryId(),
                    categoryToCommunity.getPreferenciesFactor()
            );
        } else {
            throw new NotFoundException("CategoryToCommunity not found with communityId: " + communityId + " and categoryId: " + categoryId);
        }
    }


    @Override
    public void UpdatePreferenciesFactors(String communityId , Long qte , List<ProductToCategory> categories) {

        for(ProductToCategory category : categories){
            Long categoryId = category.getCategoryId();
            System.out.println("///"+categoryId+"/"+communityId+"/"+qte+"/"+category.toString() );
            Optional<CategoryToCommunity> optionalCategoryToCommunity= repository.findByCommunityIdAndCategoryId(communityId,categoryId);

            if(optionalCategoryToCommunity.isPresent()){
                CategoryToCommunity categoryToCommunity = repository.save(new CategoryToCommunity(
                        optionalCategoryToCommunity.get().getId(),
                        optionalCategoryToCommunity.get().getCommunityId(),
                        optionalCategoryToCommunity.get().getCategoryId(),
                        optionalCategoryToCommunity.get().getPreferenciesFactor()+qte
                        )
                );
            } else{
                CategoryToCommunity ctoc = new CategoryToCommunity();
                ctoc.setCategoryId(categoryId);
                ctoc.setCommunityId(communityId);
                ctoc.setPreferenciesFactor(qte);
                repository.save(ctoc);
            }
        }
        new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public void add(CategoryToCommunity catToCom) {
        updatePreferenciesFactors(catToCom);

        repository.save(catToCom);
    }

    private void updatePreferenciesFactors(CategoryToCommunity catToCom) {
        if (catToCom.getPreferenciesFactor() == null) {
            catToCom.setPreferenciesFactor(1L); //
        }
    }

}
