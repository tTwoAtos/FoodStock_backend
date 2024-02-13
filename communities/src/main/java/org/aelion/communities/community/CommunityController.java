package org.aelion.communities.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/communities")
public class CommunityController {
    @Autowired
    private CommunityService service;

    @GetMapping
    public List<Community> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCommunity(@RequestBody Community community) {
        return service.createCommunity(community);
    }
}
