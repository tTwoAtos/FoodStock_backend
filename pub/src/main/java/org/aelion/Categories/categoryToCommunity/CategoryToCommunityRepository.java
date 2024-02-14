package org.aelion.Categories.categoryToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryToCommunityRepository extends JpaRepository<CategoryToCommunity, String> {
    List<CategoryToCommunity> findAllByCommunityId(String communityId);
    List<CategoryToCommunity> findAllByCommunityIdByOrderByPreferenciesFactorDesc(String communityId);
}
