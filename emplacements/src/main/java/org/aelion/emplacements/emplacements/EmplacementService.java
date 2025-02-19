package org.aelion.emplacements.emplacements;

import java.util.List;

public interface EmplacementService {
    List<Emplacement> getAllByCommunityId(String communityId);

    Emplacement getEmplacementById(Long id);

    void add(String communityId , String name);

    void delete(Long id);
}
