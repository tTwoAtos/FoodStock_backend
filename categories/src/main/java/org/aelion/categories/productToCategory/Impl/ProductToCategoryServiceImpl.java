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
import java.util.Random;
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
    public ResponseEntity<?> getCategoriesIdsByProductEan(String productId){

        List<ProductToCategory> ptcArrayByProductId = repository.findByProductId(productId);

        List<Long> categoriesIds = ptcArrayByProductId.stream().map((ProductToCategory cat) -> cat.getCategoryId()).toList();

        if(categoriesIds.isEmpty())
            return new ResponseEntity<>("Not categories was found with this product id " , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(categoriesIds, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> getProductsByCategoryId(Long categoryId) {
        List<ProductToCategory> res = repository.findByCategoryId(categoryId);

        System.out.println(res);

        int rand = new Random().ints(0, res.size()).findFirst().getAsInt();

        if(res.isEmpty())
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res.get(rand), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRelatedCategories(Long categoryId) {
        Object relatedProducts = getProductsByCategoryId(categoryId);

        System.out.println(relatedProducts);
//        int rand = new Random().ints(0, relatedProducts.size()).findFirst().getAsInt();

//        ProductToCategory randomProduct = relatedProducts.get(rand);

//        List<ProductToCategory> relatedCategories = (List<ProductToCategory>) getCategoriesIdsByProductEan(randomProduct.getProductId()).getBody();
//
//        if (relatedCategories.isEmpty())
//            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(relatedCategories, HttpStatus.OK);
        return null;
    }
}
