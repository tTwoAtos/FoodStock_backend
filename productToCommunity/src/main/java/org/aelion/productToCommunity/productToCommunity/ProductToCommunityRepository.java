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

    Optional<ProductToCommunity> findByCommunityIdAndProductId(String communityId, String productId);

    List<ProductToCommunity> findAllByCommunityIdOrderByProductId(String communityId);

    List<ProductToCommunity> findAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    Integer countByCommunityIdAndEmplacementId(String communityId, String emplacementId);

    void deleteByProductIdAndCommunityId(String productId, String communityId);

    @Modifying
    @Transactional
    @Query(value = "delete from product_to_community where product_id in ?1 and community_id = ?2", nativeQuery = true)
    void deleteAllByProductIdsForCommunity(List<String> ids, String communityId);
}
