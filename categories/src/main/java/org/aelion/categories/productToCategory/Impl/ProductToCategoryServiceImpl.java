package org.aelion.categories.productToCategory.Impl;

import org.aelion.categories.categories.CategoryRepository;
import org.aelion.categories.productToCategory.ProductToCategory;
import org.aelion.categories.productToCategory.ProductToCategoryRepository;
import org.aelion.categories.productToCategory.ProductToCategoryService;
import org.aelion.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.aelion.exception.NotFoundException;

import java.util.*;

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
    public ProductToCategory getById(String code) {
        return null;
    }

    @Override
    public ProductToCategory add(String productEan, List<Long> categoriesIds) {
        if (categoriesIds == null || categoriesIds.isEmpty()) {
            throw new BadRequestException("La liste des catégories ne peut pas être vide.");
        }

        List<ProductToCategory> list = new ArrayList<>();

        for (Long categoryId : categoriesIds) {
            ProductToCategory pdc = new ProductToCategory();
            pdc.setProductId(productEan);
            pdc.setCategoryId(categoryId);
            list.add(pdc);
        }

        List<ProductToCategory> savedProductsToCategories = repository.saveAll(list);

        if (savedProductsToCategories.isEmpty()) {
            throw new NotFoundException("Aucune association produit-catégorie n'a été enregistrée.");
        }

        return (ProductToCategory) savedProductsToCategories;
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

        return getCategoriesIdsByProductEan(relatedProduct.getProductId());
    }
}
