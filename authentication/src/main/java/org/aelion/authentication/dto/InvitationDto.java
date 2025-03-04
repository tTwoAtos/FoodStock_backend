package org.aelion.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationDto {
    private Long id;
    private CommunityDto community;
    private UserDto user;
    private String code;
}
