package org.aelion.categories.productToCategory;

import org.aelion.categories.productToCategory.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories/products")
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping
    public List<ProductToCategory> getAll() {
        return service.getAll();
    }

    @PostMapping("/{productEan}")
    public ResponseEntity<?> add(@PathVariable String productEan, @RequestBody CategoriesDto dto) {
        return service.add(productEan, dto.getCategoriesIds());
    }

}
