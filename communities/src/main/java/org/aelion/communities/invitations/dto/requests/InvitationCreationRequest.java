package org.aelion.communities.invitations.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationCreationRequest {
    Integer communityId;
    String userEmail;
}
