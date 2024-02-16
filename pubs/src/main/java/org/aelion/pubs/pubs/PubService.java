package org.aelion.pubs.pubs;

import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CommunityDto;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PubService {

    List<CategoryDto> findAllCategoriesOfCommunity(CommunityDto community);

    List<CategoryDto> getTop5OfTheCategoriesInACommunity(CommunityDto community);

    CommunityDto getCommunity(String communityId) throws Exception;

    ResponseEntity<?> getPubProduct(String communityId);
}
