package org.aelion.categories.categoryToCommunity.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categoryToCommunity.CategoryToCommunity;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityService;
import org.aelion.categories.productToCategory.ProductToCategory;
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

        return repository.findTop5ByCommunityIdOrderByPreferenciesFactorDesc(communityId);
    }

    @Override
    public ResponseEntity<?> getByCommunityIdAndCategoryId(String communityId, Long categoryId) {
        Optional<CategoryToCommunity> optionalCategoryToCommunity= repository.findByCommunityIdAndCategoryId(communityId,categoryId);
        if(optionalCategoryToCommunity.isPresent()) {
            CategoryToCommunity categoryToCommunity = new CategoryToCommunity(
                    optionalCategoryToCommunity.get().getId(),
                    optionalCategoryToCommunity.get().getCommunityId(),
                    optionalCategoryToCommunity.get().getCategoryId(),
                    optionalCategoryToCommunity.get().getPreferenciesFactor()
            );

            return new ResponseEntity<>(categoryToCommunity, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> UpdatePreferenciesFactors(String communityId , Long qte , List<ProductToCategory> categories) {

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
            }else{
                CategoryToCommunity ctoc = new CategoryToCommunity();
                ctoc.setCategoryId(categoryId);
                ctoc.setCommunityId(communityId);
                ctoc.setPreferenciesFactor(qte);
                repository.save(ctoc);
            }
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> add(CategoryToCommunity catToCom) {
        //Use UpdatePreferencisFactors
        return null;
    }
}
