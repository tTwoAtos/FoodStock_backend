package org.aelion.pubs.pubs;

import org.aelion.pubs.pubs.dto.ProductDto;

public interface PubService {
    ProductDto getPubProduct(String communityId) throws Exception;
}
