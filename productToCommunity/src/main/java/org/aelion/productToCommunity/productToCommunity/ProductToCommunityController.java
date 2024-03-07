package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.aelion.productToCommunity.productToCommunity.dto.QuantityDto;
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

    @GetMapping("/{communityId}")
    public Iterable<ProductResponseDto> getAllByCommunity(@PathVariable String communityId) {
        return service.getAllByCommunityId(communityId);
    }

    @GetMapping("/{communityId}/{emplacementId}")
    public Iterable<ProductResponseDto> getAllByCommunityByEmplacement(@PathVariable String communityId,@PathVariable String emplacementId){
        return  service.getAllByCommunityIdAndEmplacementId(communityId,emplacementId);
    }

    @PostMapping("/{communityId}")
    public ResponseEntity<?> add(@PathVariable String communityId, @RequestBody ProductToCommunity PtoC) {
        PtoC.setCommunityId(communityId);

        System.out.println(PtoC.getProductId() + "/" + PtoC.getCommunityId() + "//" + PtoC.getQte());
        return service.add(PtoC);
    }

    @Transactional
    @PutMapping("/{communityId}/{productId}")
    public ResponseEntity<?> updateQuantity(@PathVariable String communityId, @PathVariable String productId, @RequestBody QuantityDto QDto) {
        System.out.println(communityId +" "+ productId);
        return new ResponseEntity<>(service.updateQuantity(communityId, productId, QDto.getQte()), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{productEanCode}")
    public ResponseEntity<?> delete(@PathVariable String productEanCode) {
        service.delete(productEanCode);
        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<?> massDelete(@RequestBody List<String> codes) {
        service.massDelete(codes);
        return new ResponseEntity<>("Products Deleted", HttpStatus.OK);
    }
}
