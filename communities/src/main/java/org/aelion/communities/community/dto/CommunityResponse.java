package org.aelion.communities.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityResponse {
    private String id;
    private String name;
    private City city;
}
