package org.aelion.productToCommunity.productToCommunity.Impl;

import org.aelion.productToCommunity.productToCommunity.ProductToCommunity;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityRepository;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityService;
import org.aelion.productToCommunity.productToCommunity.dto.Community;
import org.aelion.productToCommunity.productToCommunity.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductToCommunityServiceImpl implements ProductToCommunityService {
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";
    private final static String PRODUCT_API = "http://PRODUCT-SERVICE/api/v1/products";
    private final static String CATEGORY_API = "http://CATEGORY-SERVICE/api/v1/categories";
    @Autowired
    private ProductToCommunityRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ProductToCommunity> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> add(ProductToCommunity PtoC) {
        Community community = restTemplate.getForObject(COMMUNITY_API + '/' + PtoC.getCommunityId(), Community.class);
        Product product = restTemplate.getForObject(PRODUCT_API + '/' + PtoC.getProductId(), Product.class);

        if (community == null || product == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        restTemplate.postForObject(PRODUCT_API + "/addedToCommunity/" + PtoC.getProductId(), "", String.class);
        List<String> productCategories = restTemplate.getForObject(CATEGORY_API + "/products/" + PtoC.getProductId(), List.class);
        restTemplate.postForObject(CATEGORY_API + "/community/" + community.getId(), productCategories, String.class);

        ProductToCommunity res = repository.save(PtoC);

        if (res == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
