package org.aelion.productToCommunity.productToCommunity;

import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductToCommunityService {
    List<ProductToCommunity> getAll();

    List<ProductResponseDto> getAllByCommunityId(String communityId);

    List<ProductResponseDto> getAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    Integer countAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    ResponseEntity<?> add(ProductToCommunity PtoC);

    ProductToCommunity updateQuantity(String communityId, String productId, Long quantity);

    void delete(String code, String communityId);

    void massDelete(List<String> codes, String communityId);

    Object getAll(Pageable pageable, Map<String, String> filters);
}
