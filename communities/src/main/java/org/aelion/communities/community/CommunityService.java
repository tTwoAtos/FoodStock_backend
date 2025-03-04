package org.aelion.communities.community;

import org.aelion.communities.community.dto.CommunityResponse;

import java.util.List;

public interface CommunityService {
    List<Community> getAll();

    CommunityResponse getById(Integer id);

    Community createCommunity(Community community);
}
