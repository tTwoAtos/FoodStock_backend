package org.aelion.pubs.pubs.Impl;

import org.aelion.pubs.pubs.PubService;
import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CategoryToCommunityDto;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.aelion.pubs.pubs.dto.ProductToCategoryDto;
import org.aelion.pubs.pubs.strategy.PubDependingCategoriesStrategy;
import org.aelion.pubs.pubs.strategy.PubStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PubServiceImpl implements PubService {

    @Value("${API_GATEWAY}")
    String API_GATEWAY;

    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    //private String foodFactURL = "https://world.openfoodfacts.net/api/v2/search?categories_tags_en="le nom de ta categorie" &fields=code,categories_tags_en,product_name"

    String catgoriesAPI = "http://CATEGORY-SERVICE/api/v1/categories";

    @Autowired
    RestTemplate restTemplate;

    //PubStrategy strategy = new PubStrategy();

    @Override
    public List<ProductDto> getPubProduct(String communityId) {
        // 1) récup une catégory aléatoire d'une community de categoryToCommunity
        CategoryToCommunityDto[] cTocList = restTemplate.getForEntity(catgoriesAPI + "/community/" + communityId, CategoryToCommunityDto[].class).getBody();

        if(cTocList.length == 0)
        {
            return null;
        }

        int rand = new Random().ints(0, cTocList.length).findFirst().getAsInt();

        CategoryToCommunityDto randomCategory = cTocList[rand];

        // 2) Récup des catégories en lien avec la catégory du 1)
        ProductToCategoryDto[] pTocArray = restTemplate.getForEntity(catgoriesAPI + "/products/related/" + randomCategory.getCategoryId(), ProductToCategoryDto[].class).getBody();

        if(pTocArray.length == 0) {
            return null;
        }

        // 3) Choisir 3 catégories random dans cette liste, et faire un appel a OpenFoodFact pour récup la liste des produit avec ces 3 catégories
        List<ProductToCategoryDto> randCategories = new ArrayList<>();

        List<ProductToCategoryDto> pTocList = new ArrayList<ProductToCategoryDto>(Arrays.asList(pTocArray));

        System.out.println("Product to categories list size : " + pTocList.size());
        for (int i = 0; i < pTocList.size(); i++) {
            int randomIndex = new Random().ints(0, pTocList.size()).findFirst().getAsInt();
            ProductToCategoryDto randomElement = pTocList.get(randomIndex);
            randCategories.add(randomElement);
            pTocList.remove(randomIndex);
        }

        // 4) Retourner un produit pub random de cette liste

        return null;

    }

    private ProductDto getFromOpenFoodFact(String categoriesName) {
        RestTemplate tmpRestTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = tmpRestTemplate.exchange(foodFactApi + '/' + "search?categories_tags_en=" + categoriesName + "&fields=code,categories_tags_en,product_name",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {
        });

        if (!response.hasBody()) {
            return null;
        }

        Map<String, Object> body = response.getBody();

        String name = (String) ((Map<String, Object>) body.get("product")).get("generic_name");
        if (name == null)
            name = (String) ((Map<String, Object>) body.get("product")).get("product_name");

        String thumbnail = (String) ((Map<String, Object>) body.get("product")).get("image_thumb_url");
        List<String> categories = (List<String>) ((Map<List<String>, Object>) body.get("product")).get("categories_tags");

        // TODO : Change with the real Product
        return new ProductDto();
    }
}
