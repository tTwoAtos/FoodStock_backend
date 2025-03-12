package org.aelion.productToCommunity.productToCommunity.Impl;

import org.aelion.exception.NotFoundException;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunity;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityRepository;
import org.aelion.productToCommunity.productToCommunity.ProductToCommunityService;
import org.aelion.productToCommunity.productToCommunity.dto.Community;
import org.aelion.productToCommunity.productToCommunity.dto.ProductDto;
import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<ProductResponseDto> getAllByCommunityId(Integer communityId) {
        List<ProductToCommunity> productToCommunity = repository.findAllByCommunityIdOrderByProductId(communityId);

        List<String> productIds = productToCommunity.stream().map((pToC) -> pToC.getProductId()).toList();

        ProductDto[] products = restTemplate.postForObject(PRODUCT_API + "/list", productIds, ProductDto[].class);

        // Ensure lists are in the same order

        List<ProductResponseDto> response = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            ProductDto product = products[i];
            Long quantity = productToCommunity.get(i).getQte();

            response.add(new ProductResponseDto(product.getEANCode(), product.getName(), product.getNbScanned(), product.getThumbnail(), product.getNbAdded(), quantity, productToCommunity.get(i).getEmplacementId()));
        }

        return response;
    }

    @Override
    public List<ProductResponseDto> getAllByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId) {
        List<ProductToCommunity> productToCommunity = repository.findAllByCommunityIdAndEmplacementId(communityId, emplacementId);

        List<String> productIds = productToCommunity.stream().map((pToC) -> pToC.getProductId()).toList();

        ProductDto[] products = restTemplate.postForObject(PRODUCT_API + "/list", productIds, ProductDto[].class);

        List<ProductResponseDto> response = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            ProductDto product = products[i];
            Long quantity = productToCommunity.stream()
                    .filter(ptc -> ptc.getProductId().equals(product.getEANCode()))
                    .findFirst().get().getQte();

            response.add(new ProductResponseDto(product.getEANCode(), product.getName(), product.getNbScanned(), product.getThumbnail(), product.getNbAdded(), quantity, productToCommunity.get(i).getEmplacementId()));
        }

        return response;
    }

    @Override
    public Integer countAllByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId) {
        return repository.sumQuantityByCommunityIdAndEmplacementId(communityId, emplacementId);
    }


    @Override
    public ProductToCommunity add(ProductToCommunity PtoC) {
        Community community = restTemplate.getForObject(COMMUNITY_API + '/' + PtoC.getCommunityId(), Community.class);
        ProductDto productDto = restTemplate.getForObject(PRODUCT_API + '/' + PtoC.getProductId(), ProductDto.class);

        if (community == null || productDto == null) {
            throw new NotFoundException("Communauté ou produit non trouvé");
        }

        restTemplate.postForObject(PRODUCT_API + "/addedToCommunity/" + PtoC.getProductId(), "", String.class);
        List productCategoriesIds = restTemplate.getForObject(CATEGORY_API + "/products/" + PtoC.getProductId(), List.class);
        restTemplate.postForObject(CATEGORY_API + "/community/" + community.getId() + "/" + PtoC.getQte(), productCategoriesIds, String.class);

        return repository.save(PtoC);
    }


    @Override
    public ProductToCommunity updateQuantity(Integer communityId, String productId, Long quantity) {
        ProductToCommunity pToC = repository.findByCommunityIdAndProductId(communityId, productId).orElseThrow();

        pToC.setQte(quantity);

        if (quantity == 0)
            repository.deleteById(pToC.getId());
        else
            return repository.save(pToC);
        return null;
    }

    @Override
    public void delete(String code, Integer communityId) {
        repository.deleteByProductIdAndCommunityId(code, communityId);
    }

    @Override
    public void massDelete(List<String> codes, Integer communityId) {
        repository.deleteAllByProductIdsForCommunity(codes, communityId);
    }

    @Override
    public Object getAll(Pageable pageable, Map<String, String> filters) {
        return null;
    }
}
