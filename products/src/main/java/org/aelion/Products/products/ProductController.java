package org.aelion.Products.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping("/list")
    public List<Product> getAllByIdList(@RequestBody List<String> productIds) {
        return service.getAllByIdList(productIds);
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
