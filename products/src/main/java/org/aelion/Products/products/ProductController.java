package org.aelion.Products.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getById(@PathVariable String code) {
        return service.getById(code);
    }
    
    @PostMapping("/addedToCommunity/{code}")
    public ResponseEntity<?> addedToCommunity(@PathVariable String code) {
        return service.addedToCommunity(code);
    }

}
