package org.aelion.categories.categories;

import org.aelion.categories.categories.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("{productCode}")
    public ResponseEntity<?> add(@RequestBody List<String> categories, @PathVariable String productCode) {
        return service.add(categories, productCode);
    }
}
