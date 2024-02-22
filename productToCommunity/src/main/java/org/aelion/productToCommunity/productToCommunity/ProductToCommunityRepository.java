package org.aelion.productToCommunity.productToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductToCommunityRepository extends JpaRepository<ProductToCommunity, String> {
    Optional<ProductToCommunity> findByProductId(String productId);

    void deleteByProductId(String productId);
}
