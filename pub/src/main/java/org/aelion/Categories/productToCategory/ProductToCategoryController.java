package org.aelion.Categories.productToCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productToCategories")
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping
    public List<ProductToCategory> getAll() {
        return service.getAll();
    }

    @PostMapping("/{productEan}")
    public ResponseEntity<?> add(@PathVariable String productEan, @RequestBody List<String> categoriesIds) {
        return service.add(productEan, categoriesIds);
    }

}
