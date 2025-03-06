package org.aelion.emplacements.emplacements;

import java.util.List;

public interface EmplacementService {
    List<Emplacement> getAllByCommunityId(Integer communityId);

    Emplacement getEmplacementById(Long id);

    void add(Integer communityId , String name);

    void delete(Long id);
}
