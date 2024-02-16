package org.aelion.productToCommunity.productToCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-to-community")
public class ProductToCommunityController {
    @Autowired
    private ProductToCommunityService service;

    @GetMapping
    public List<ProductToCommunity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> getById(@RequestBody ProductToCommunity PtoC) {
        return service.add(PtoC);
    }




}
