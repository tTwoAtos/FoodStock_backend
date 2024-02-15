package org.aelion.pubs.pubs.Impl;

import org.aelion.pubs.pubs.PubService;
import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CommunityDto;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PubServiceImpl implements PubService {

    @Value("${API_GATEWAY}")
    String API_GATEWAY;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<CategoryDto> findAllCategoriesOfCommunity(CommunityDto community) {
        return null;
    }

    @Override
    public List<CategoryDto> getTop5OfTheCategoriesInACommunity(CommunityDto community) {
        return null;
    }

    @Override
    public CommunityDto getCommunity(String communityId) throws Exception {

        CommunityDto community = restTemplate.getForObject(API_GATEWAY + "/communities/" + communityId, CommunityDto.class);

        if(community == null)
            throw new Exception("There isn't community");

        return community;
    }

    @Override
    public ResponseEntity<?> getPubProduct(String communityId) {
        // 1) récup une catégory aléatoire d'une community de categoryToCommunity
        restTemplate.getForObject(API_GATEWAY + "/categoryToCommunity/" + communityId, CommunityDto.class);

        // 2) récup un produit aléatoire de productToCategory qui possède la catégory du 1)
        // 3) récup tt les catégories de ce produit dans productToCategory
        // 4) Choisir 3 catégories random dans cette liste, et faire un appel a OpenFoodFact pour récup la liste des produit avec ces 3 catégories
        // 5) Retourner un produit pub randon de cette liste

        return null;
    }
}
