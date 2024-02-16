package org.aelion.pubs.pubs.strategy;

import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CategoryToCommunityDto;
import org.aelion.pubs.pubs.dto.CommunityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

public class PubDependingCategoriesStrategy extends PubStrategy {
    @Value("${API_GATEWAY}")
    String API_GATEWAY;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> getPubProduct(String communityId) {
        // 1) récup une catégory aléatoire d'une community de categoryToCommunity
        List<CategoryToCommunityDto> cTocList = restTemplate.getForObject(API_GATEWAY + "/categories/community/" + communityId, List.class);

        int rand = new Random().ints(0, cTocList.size()).findFirst().getAsInt();

        CategoryToCommunityDto randomCategory = cTocList.get(rand);
        System.out.println(randomCategory.getCategoryId());
        // 2) récup un produit aléatoire de productToCategory qui possède la catégory du 1)


        // 3) récup tt les catégories de ce produit dans productToCategory
        // 4) Choisir 3 catégories random dans cette liste, et faire un appel a OpenFoodFact pour récup la liste des produit avec ces 3 catégories
        // 5) Retourner un produit pub randon de cette liste

        return null;
    }
}
