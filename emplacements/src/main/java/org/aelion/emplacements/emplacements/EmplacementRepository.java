package org.aelion.emplacements.emplacements;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {
    List<Emplacement> findAllByCommunityId(String communityId);

    void deleteById(Long id);

}
