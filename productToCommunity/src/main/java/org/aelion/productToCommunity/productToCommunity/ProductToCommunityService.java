package org.aelion.productToCommunity.productToCommunity;

import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductToCommunityService {
    List<ProductToCommunity> getAll();

    List<ProductResponseDto> getAllByCommunityId(String communityId);

    List<ProductResponseDto> getAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    Integer countAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    ResponseEntity<?> add(ProductToCommunity PtoC);

    ProductToCommunity updateQuantity(String communityId, String productId, Long quantity);

    void delete(String code, String communityId);

    void massDelete(List<String> codes, String communityId);
}
