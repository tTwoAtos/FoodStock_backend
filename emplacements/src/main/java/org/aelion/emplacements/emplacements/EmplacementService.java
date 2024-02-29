package org.aelion.emplacements.emplacements;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmplacementService {
    List<Emplacement> getAllByCommunityId(String communityId);

    ResponseEntity<?> getEmplacementById(Long id);

    ResponseEntity<?> add(String communityId ,String name);

    void delete(Long id);
}
