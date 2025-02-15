package org.aelion.productToCommunity.productToCommunity;

import org.springframework.data.jpa.domain.Specification;

public class ProductToCommunitySpecifications {

    // Filtrer par productId
    public static Specification<ProductToCommunity> productIdLike(String productId) {
        return (root, query, builder) -> {
            if (productId == null || productId.isEmpty()) {
                return builder.conjunction(); // Renvoie une condition toujours vraie si productId est vide
            }
            return builder.like(root.get("productId"), "%" + productId + "%");
        };
    }

    // Filtrer par communityId
    public static Specification<ProductToCommunity> communityIdLike(String communityId) {
        return (root, query, builder) -> {
            if (communityId == null || communityId.isEmpty()) {
                return builder.conjunction(); // Renvoie une condition toujours vraie si communityId est vide
            }
            return builder.like(root.get("communityId"), "%" + communityId + "%");
        };
    }
}
