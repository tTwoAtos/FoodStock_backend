package org.aelion.migration.dto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmplacementRepositoryDto extends JpaRepository<EmplacementDto, Long> {
//    List<EmplacementDto> findAllByCommunityId(String communityId);
//
//    void deleteById(Long id);

}
