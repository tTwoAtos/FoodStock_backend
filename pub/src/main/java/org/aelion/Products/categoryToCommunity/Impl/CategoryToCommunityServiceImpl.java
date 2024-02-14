package org.aelion.Products.categoryToCommunity.Impl;

import org.aelion.Products.categoryToCommunity.CategoryToCommunity;
import org.aelion.Products.categoryToCommunity.CategoryToCommunityRepository;
import org.aelion.Products.categoryToCommunity.CategoryToCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        //return repository.findAll(communityId,)
        return null;
    }

    @Override
    public ResponseEntity<?> add(CategoryToCommunity catToCom) {
        return null;
    }
}
