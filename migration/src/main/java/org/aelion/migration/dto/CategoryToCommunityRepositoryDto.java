package org.aelion.migration.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryToCommunityRepositoryDto extends JpaRepository<CategoryToCommunityDto, String> {

//    List<CategoryToCommunityDto> findTop20ByCommunityIdOrderByPreferenciesFactorDesc(String communityId);
//
//    Optional<CategoryToCommunityDto> findByCommunityIdAndCategoryId(String communityId, Long categoryId);
}
