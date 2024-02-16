package org.aelion.categories.productToCategory.Impl;

import org.aelion.categories.categories.Category;
import org.aelion.categories.categories.CategoryRepository;
import org.aelion.categories.productToCategory.ProductToCategory;
import org.aelion.categories.productToCategory.ProductToCategoryRepository;
import org.aelion.categories.productToCategory.ProductToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.random.RandomGenerator;

@Service
public class ProductToCategoryServiceImpl implements ProductToCategoryService {
    @Autowired
    private ProductToCategoryRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public List<ProductToCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> add(String productEan, List<Long> categoriesIds) {

        List<ProductToCategory> list = new ArrayList<ProductToCategory>();

        for(Long categoriesId : categoriesIds){
            ProductToCategory pdc = new ProductToCategory();
            pdc.setProductId(productEan);
            pdc.setCategoryId(categoriesId);
            list.add(pdc);
        }

        List<ProductToCategory> repositoryPdc = repository.saveAll(list);

        if(repositoryPdc.isEmpty())
            return new ResponseEntity<>("Not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(repositoryPdc, HttpStatus.CREATED);
    }

    @Override
    public List<ProductToCategory> getCategoriesIdsByProductEan(String productId){

        List<ProductToCategory> ptcArrayByProductId = repository.findByProductId(productId);

        if(ptcArrayByProductId.isEmpty())
            return null;

        return ptcArrayByProductId;
    }

    @Override
    public ProductToCategory getProductsByCategoryId(Long categoryId) {
        List<ProductToCategory> res = repository.findByCategoryId(categoryId);

        if(res.isEmpty())
            return null;

        int rand = new Random().ints(0, res.size()).findFirst().getAsInt();

        return res.get(rand);
    }

    @Override
    public List<ProductToCategory> getRelatedCategories(Long categoryId) {
        ProductToCategory relatedProduct = getProductsByCategoryId(categoryId);

        if (relatedProduct == null)
            return null;

        List<ProductToCategory> relatedCategories = getCategoriesIdsByProductEan(relatedProduct.getProductId());

        return relatedCategories;
    }
}
