package org.aelion.pubs.pubs;

import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CommunityDto;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.aelion.pubs.pubs.strategy.PubDependingCategoriesStrategy;
import org.aelion.pubs.pubs.strategy.PubStrategy;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * To be able to use this service, the method "SetStrategy()" musts to be used at first
 */
public interface PubService {
    List<ProductDto> getPubProduct(String communityId);
}
