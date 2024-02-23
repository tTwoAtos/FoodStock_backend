package org.aelion.productToCommunity.productToCommunity;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductToCommunityService {
    List<ProductToCommunity> getAll();

    ResponseEntity<?> add(ProductToCommunity PtoC);

    ProductToCommunity updateQuantity(String code, Long quantity);

    void delete(String code);

    void massDelete(List<String> codes);
}
