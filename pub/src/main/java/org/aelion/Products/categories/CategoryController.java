package org.aelion.Products.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @PostMapping("{productEan}")
    public ResponseEntity<?> add(@RequestBody List<Category> categories, @PathVariable String productEan) {
        return service.add(categories, productEan);
    }

}
