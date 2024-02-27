package org.aelion.productToCommunity.productToCommunity;

import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductToCommunityService {
    List<ProductToCommunity> getAll();

    List<ProductResponseDto> getAllByCommunityId(String communityId);

    ResponseEntity<?> add(ProductToCommunity PtoC);

    ProductToCommunity updateQuantity(Long id, Long quantity);

    void delete(String code);

    void massDelete(List<String> codes);
}
