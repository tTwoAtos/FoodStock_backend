package org.aelion.categories.categoryToCommunity;

import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryToCommunityRepository extends JpaRepository<CategoryToCommunity, String> {

    List<CategoryToCommunity> findTop20ByCommunityIdOrderByPreferenciesFactorDesc(String communityId);

    Optional<CategoryToCommunity> findByCommunityIdAndCategoryId(String communityId, Long categoryId);
}
