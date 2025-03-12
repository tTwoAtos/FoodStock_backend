package org.aelion.productToCommunity.productToCommunity;

import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductToCommunityService {
    List<ProductToCommunity> getAll();

    List<ProductResponseDto> getAllByCommunityId(Integer communityId);

    List<ProductResponseDto> getAllByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId);

    Integer countAllByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId);

    public ProductToCommunity add(ProductToCommunity PtoC);

    ProductToCommunity updateQuantity(Integer communityId, String productId, Long quantity);

    void delete(String code, Integer communityId);

    void massDelete(List<String> codes, Integer communityId);

    Object getAll(Pageable pageable, Map<String, String> filters);
}
