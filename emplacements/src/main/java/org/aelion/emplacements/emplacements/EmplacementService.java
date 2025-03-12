package org.aelion.emplacements.emplacements;

import org.aelion.emplacements.emplacements.dto.EmplacementListResponse;

import java.util.List;

public interface EmplacementService {
    List<EmplacementListResponse> getAllByCommunityId(Integer communityId);

    Emplacement getEmplacementById(Long id);

    void add(Integer communityId, String name);

    void delete(Long id);
}
