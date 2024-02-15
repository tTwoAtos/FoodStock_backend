package org.aelion.categories.categoryToCommunity.Impl;

import org.aelion.categories.categoryToCommunity.CategoryToCommunity;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.categories.categoryToCommunity.CategoryToCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryToCommunityServiceImpl implements CategoryToCommunityService {
    @Autowired
    private CategoryToCommunityRepository repository;

    @Override
    public List<CategoryToCommunity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CategoryToCommunity> getAllCategoriesByCommunityId(String communityId) {
        return
                repository.findByCommunityId(communityId);
    }
    @Override
    public ResponseEntity<?> add(CategoryToCommunity catToCom) {
        return null;
    }
}
