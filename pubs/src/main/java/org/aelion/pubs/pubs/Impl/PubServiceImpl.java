package org.aelion.pubs.pubs.Impl;

import org.aelion.pubs.pubs.PubService;
import org.aelion.pubs.pubs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PubServiceImpl implements PubService {

    @Value("${API_GATEWAY}")
    String API_GATEWAY;

    @Value("${OPEN_FOOD_FACT_API}")
    private String foodFactApi;

    String catgoriesAPI = "http://CATEGORY-SERVICE/api/v1/categories";

    @Autowired
    RestTemplate restTemplate;

    //PubStrategy strategy = new PubStrategy();

    @Override
    public ProductDto getPubProduct(String communityId) {
        // 1) récup une catégory aléatoire d'une community de categoryToCommunity
        CategoryToCommunityDto[] cTocList = restTemplate.getForEntity(catgoriesAPI + "/community/" + communityId, CategoryToCommunityDto[].class).getBody();

        if (cTocList.length == 0) {
            return null;
        }

        int rand = new Random().ints(0, cTocList.length).findFirst().getAsInt();

        CategoryToCommunityDto randomCategory = cTocList[rand];

        // 2) Récup des catégories en lien avec la catégory du 1)
        ProductToCategoryDto[] pTocArray = restTemplate.getForEntity(catgoriesAPI + "/products/related/" + randomCategory.getCategoryId(), ProductToCategoryDto[].class).getBody();

        if (pTocArray.length == 0) {
            return null;
        }

        // 3) Choisir 3 catégories random dans cette liste, et faire un appel a OpenFoodFact pour récup la liste des produit avec ces 3 catégories
        List<ProductToCategoryDto> randCategoriesId = new ArrayList<>();

        List<ProductToCategoryDto> pTocList = new ArrayList<ProductToCategoryDto>(Arrays.asList(pTocArray));

        for (int i = 0; i < pTocList.size(); i++) {
            int randomIndex = new Random().ints(0, pTocList.size()).findFirst().getAsInt();
            ProductToCategoryDto randomElement = pTocList.get(randomIndex);
            randCategoriesId.add(randomElement);
            pTocList.remove(randomIndex);
        }

        // 4) Retourner un produit pub random de cette liste
        String categories = "";

        for (ProductToCategoryDto cat : randCategoriesId ) {
            CategoryDto category = restTemplate.getForEntity(catgoriesAPI + "/"+ cat.getCategoryId(), CategoryDto.class).getBody();
            categories += category.getName() + ',';
        }

        List<ProductDto> pubs = getFromOpenFoodFact(categories);
        List<ProductDto> finalPubs = pubs;
        pubs = pubs.stream().filter((pub1) -> finalPubs.stream().map((pub2) -> pub2.getEANCode()).toList().contains(pub1.getEANCode())).toList();

        int randPub = new Random().ints(0, pubs.size()).findFirst().getAsInt();

        pubs.stream().forEach((pub)-> System.out.println(pub.getEANCode()));
        return pubs.get(randPub);
    }

    private List<ProductDto> getFromOpenFoodFact(String categoriesName) {
        RestTemplate tmpRestTemplate = new RestTemplate();

        FoodFactReturnDto response = tmpRestTemplate.getForObject(foodFactApi + '/' + "search?categories_tags_en=" + categoriesName + "&fields=code,product_name,generic_name,image_thumb_url", FoodFactReturnDto.class);

        List<OpenFoodProductDto> products = response.getProducts();

        List<ProductDto> resProducts = new ArrayList<>();
        for (OpenFoodProductDto product : products) {
            ProductDto prod = new ProductDto();

            prod.setName(product.getGeneric_name());
            if (prod.getName() == null || prod.getName() == "")
                prod.setName(product.getProduct_name());

            prod.setEANCode(product.getCode());
            prod.setThumbnail(product.getImage_thumb_url());

            resProducts.add(prod);
        }

        resProducts.stream().filter((product)->!resProducts.contains(product));

        System.out.println(resProducts);

        return resProducts;
    }
}
