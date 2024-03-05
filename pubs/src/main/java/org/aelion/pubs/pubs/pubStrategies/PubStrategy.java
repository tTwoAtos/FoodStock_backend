package org.aelion.pubs.pubs.pubStrategies;

import org.aelion.pubs.pubs.dto.ProductDto;

public interface PubStrategy {
    ProductDto getPub(String communityId) throws Exception;
}
