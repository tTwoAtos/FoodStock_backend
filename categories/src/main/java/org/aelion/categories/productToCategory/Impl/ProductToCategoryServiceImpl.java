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
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<?> add(String productEan, List<String> categoriesIds) {

        List<ProductToCategory> list = new ArrayList<ProductToCategory>();

        for(String categoriesId : categoriesIds){
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
    public ResponseEntity<?> getCategoriesIdsByProductEan(String productId){

        List<ProductToCategory> ptcArrayByProductId = repository.findByProductId(productId);

        List<String> categoriesIds = ptcArrayByProductId.stream().map((ProductToCategory cat) -> cat.getCategoryId()).toList();

        if(categoriesIds.isEmpty())
            return new ResponseEntity<>("Not categories was found with this product id " , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(categoriesIds, HttpStatus.FOUND);
    }
}
