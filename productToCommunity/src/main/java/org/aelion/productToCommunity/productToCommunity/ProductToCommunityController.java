package org.aelion.productToCommunity.productToCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> add(@RequestBody ProductToCommunity PtoC) {
        return service.add(PtoC);
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> updateQuantity(@PathVariable String code, @RequestBody ProductToCommunity PtoC) {
        PtoC.setProductId(code);
        return new ResponseEntity<>(service.updateQuantity(code, PtoC.getQte()), HttpStatus.OK);
    }
}
