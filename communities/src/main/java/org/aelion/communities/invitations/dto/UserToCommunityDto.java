package org.aelion.communities.invitations.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToCommunityDto {
    private String id;
    private Integer communityId;
    private String userId;

    public UserToCommunityDto(Integer communityId, String userId) {
        this.communityId = communityId;
        this.userId = userId;
    }
}
