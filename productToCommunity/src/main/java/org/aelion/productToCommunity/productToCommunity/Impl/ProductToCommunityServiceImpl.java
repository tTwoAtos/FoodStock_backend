package org.aelion.productToCommunity.productToCommunity.Impl;

import org.aelion.productToCommunity.productToCommunity.ProductToCommunity;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityRepository;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityService;
import org.aelion.productToCommunity.productToCommunity.dto.Community;
import org.aelion.productToCommunity.productToCommunity.dto.ProductDto;
import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.aelion.productToCommunity.productToCommunity.dto.ProductToCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductToCommunityServiceImpl implements ProductToCommunityService {
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";
    private final static String PRODUCT_API = "http://PRODUCTS-SERVICE/api/v1/products";
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
    public List<ProductResponseDto> getAllByCommunityId(String communityId) {
        List<ProductToCommunity> productToCommunity = repository.findAllByCommunityId(communityId);

        List<String> productIds = productToCommunity.stream().map((pToC) -> pToC.getProductId()).toList();

        ProductDto[] products = restTemplate.postForObject(PRODUCT_API + "/list", productIds, ProductDto[].class);

        List<ProductResponseDto> response = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            ProductDto product = products[i];
            Long quantity = productToCommunity.get(i).getQte();

            response.add(new ProductResponseDto(product.getEANCode(), product.getName(), product.getNbScanned(), product.getThumbnail(), product.getNbAdded(), quantity, productToCommunity.get(i).getEmplacementId()));
        }

        return response;
    }

    @Override
    public List<ProductResponseDto> getAllByCommunityIdAndEmplacementId(String communityId, String emplacementId) {
        List<ProductToCommunity> productToCommunity = repository.findAllByCommunityIdAndEmplacementId(communityId, emplacementId);

        List<String> productIds = productToCommunity.stream().map((pToC) -> pToC.getProductId()).toList();

        ProductDto[] products = restTemplate.postForObject(PRODUCT_API + "/list", productIds, ProductDto[].class);

        List<ProductResponseDto> response = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            ProductDto product = products[i];
            Long quantity = productToCommunity.get(i).getQte();

            response.add(new ProductResponseDto(product.getEANCode(), product.getName(), product.getNbScanned(), product.getThumbnail(), product.getNbAdded(), quantity));
        }

        return response;
    }

    @Override
    public Integer countAllByCommunityIdAndEmplacementId(String communityId, String emplacementId) {
        return repository.countByCommunityIdAndEmplacementId(communityId, emplacementId);
    }


    @Override
    public ResponseEntity<?> add(ProductToCommunity PtoC) {
        Community community = restTemplate.getForObject(COMMUNITY_API + '/' + PtoC.getCommunityId(), Community.class);
        ProductDto productDto = restTemplate.getForObject(PRODUCT_API + '/' + PtoC.getProductId(), ProductDto.class);

        if (community == null || productDto == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        restTemplate.postForObject(PRODUCT_API + "/addedToCommunity/" + PtoC.getProductId(), "", String.class);
        List<ProductToCategory> productCategoriesIds = restTemplate.getForObject(CATEGORY_API + "/products/" + PtoC.getProductId(), List.class);
        restTemplate.postForObject(CATEGORY_API + "/community/" + community.getId() + "/" + PtoC.getQte(), productCategoriesIds, String.class);

        ProductToCommunity res = repository.save(PtoC);

        if (res == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @Override
    public ProductToCommunity updateQuantity(Long id, Long quantity) {
        ProductToCommunity pToC = repository.findById(id).orElseThrow();

        pToC.setQte(quantity);

        if (quantity == 0)
            repository.deleteById(id);
        else
            return repository.save(pToC);
        return null;
    }

    @Override
    public void delete(String code) {
        repository.deleteByProductId(code);
    }

    @Override
    public void massDelete(List<String> codes) {
//        repository.
        repository.deleteAllByProductIds(codes);
    }
}
