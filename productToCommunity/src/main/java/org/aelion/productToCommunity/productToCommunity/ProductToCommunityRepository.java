package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductToCommunityRepository extends JpaRepository<ProductToCommunity, Long> {

    Optional<ProductToCommunity> findByCommunityIdAndProductId(Integer communityId, String productId);

    List<ProductToCommunity> findAllByCommunityIdOrderByProductId(Integer communityId);

    List<ProductToCommunity> findAllByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId);

    Integer countByCommunityIdAndEmplacementId(Integer communityId, Integer emplacementId);

    @Query(value = "SELECT COALESCE(SUM(qte), 0) FROM product_to_community WHERE community_id = :communityId AND emplacement_id = :emplacementId", nativeQuery = true)
    Integer sumQuantityByCommunityIdAndEmplacementId(@Param("communityId") Integer communityId, @Param("emplacementId") Integer emplacementId);

    void deleteByProductIdAndCommunityId(String productId, Integer communityId);

    @Modifying
    @Transactional
    @Query(value = "delete from product_to_community where product_id in ?1 and community_id = ?2", nativeQuery = true)
    void deleteAllByProductIdsForCommunity(List<String> ids, Integer communityId);
}
