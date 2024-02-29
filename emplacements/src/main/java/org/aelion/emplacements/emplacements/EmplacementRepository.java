package org.aelion.emplacements.emplacements;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {

    Optional<Emplacement> findById(String id);
    List<Emplacement> findAllByCommunityId(String communityId);

}
