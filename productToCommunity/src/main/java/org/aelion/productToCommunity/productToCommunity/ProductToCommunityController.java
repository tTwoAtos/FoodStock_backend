package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
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
    public Iterable<ProductToCommunity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductToCommunity PtoC) {
        return service.add(PtoC);
    }

    @Transactional
    @PutMapping("/{code}")
    public ResponseEntity<?> updateQuantity(@PathVariable String code, @RequestBody ProductToCommunity PtoC) {
        return new ResponseEntity<>(service.updateQuantity(code, PtoC.getQte()), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{code}")
    public ResponseEntity<?> delete(@PathVariable String code) {
        service.delete(code);
        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<?> massDelete(@RequestBody List<String> codes) {
        service.massDelete(codes);
        return new ResponseEntity<>("Products Deleted", HttpStatus.OK);
    }
}
