package org.aelion.emplacements.emplacements.dto;

import lombok.Getter;
import lombok.Setter;
import org.aelion.emplacements.emplacements.Emplacement;

@Getter
@Setter
public class EmplacementListResponse {
    private Long id;
    private Integer communityId;
    private String name;
    private Integer nbProducts;

    public EmplacementListResponse(Emplacement emplacement) {
        this.id = emplacement.getId();
        this.communityId = emplacement.getCommunityId();
        this.name = emplacement.getName();
    }
}
