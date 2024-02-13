package org.aelion.communities.community;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommunityService {
    List<Community> getAll();

    ResponseEntity<?> getById(String id);

    ResponseEntity<?> createCommunity(Community community);
}
