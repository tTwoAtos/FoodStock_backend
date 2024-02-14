package org.aelion.Categories.categoryToCommunity.Impl;

import org.aelion.Categories.categoryToCommunity.CategoryToCommunity;
import org.aelion.Categories.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.Categories.categoryToCommunity.CategoryToCommunityService;
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
    public List<CategoryToCommunity> getCategorieByCommunityId(String communityId) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(CategoryToCommunity catToCom) {
        return null;
    }
}
