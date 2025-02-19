package org.aelion.communities.community;

import org.aelion.communities.community.dto.CommunityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/communities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommunityController {
    @Autowired
    private CommunityService service;

    @GetMapping
    public List<Community> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    public CommunityResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Community createCommunity(@RequestBody Community community) {
        return service.createCommunity(community);
    }
}

