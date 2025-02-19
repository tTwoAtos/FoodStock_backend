package org.aelion.categories.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }


    @GetMapping("/{categoryId}")
    public Category get(@PathVariable String categoryId) {
        return service.getById(categoryId);
    }


    @PostMapping("{productCode}")
    public void add(@RequestBody List<String> categories, @PathVariable String productCode) {
        service.add(categories, productCode);
    }

}
