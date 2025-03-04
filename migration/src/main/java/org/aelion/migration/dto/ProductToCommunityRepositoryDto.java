package org.aelion.migration.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToCommunityRepositoryDto extends JpaRepository<ProductToCommunityDto, Long> {

//    Optional<ProductToCommunityDto> findByCommunityIdAndProductId(String communityId, String productId);
//
//    List<ProductToCommunityDto> findAllByCommunityIdOrderByProductId(String communityId);
//
//    List<ProductToCommunityDto> findAllByCommunityIdAndEmplacementId(String communityId, String emplacementId);
//
//    Integer countByCommunityIdAndEmplacementId(String communityId, String emplacementId);
//
//    void deleteByProductIdAndCommunityId(String productId, String communityId);
//
//    @Modifying
//    @Transactional
//    @Query(value = "delete from product_to_community where product_id in ?1 and community_id = ?2", nativeQuery = true)
//    void deleteAllByProductIdsForCommunity(List<String> ids, String communityId);
}
