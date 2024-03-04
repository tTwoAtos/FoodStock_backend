package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductToCommunityRepository extends JpaRepository<ProductToCommunity, Long> {
    Optional<ProductToCommunity> findByProductId(String productId);

    List<ProductToCommunity> findAllByCommunityId(String communityId);

    List<ProductToCommunity> findAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    void deleteByProductId(String productId);

    @Modifying
    @Transactional
    @Query(value = "delete from product_to_community where product_id in ?1", nativeQuery = true)
    void deleteAllByProductIds(List<String> ids);
}
