package org.myownstock.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityDto {
    private Long id;

    private String name;

    private String cityCode;

    private Long nbUsers;
}
