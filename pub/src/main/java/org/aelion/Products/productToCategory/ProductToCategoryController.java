package org.aelion.Products.productToCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoryToCommunity")
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping
    public List<ProductToCategory> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable ProductToCategory prodToCom) {
        return service.add(prodToCom);
    }

}
