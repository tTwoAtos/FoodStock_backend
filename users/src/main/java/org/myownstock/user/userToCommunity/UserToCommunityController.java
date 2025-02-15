package org.myownstock.user.userToCommunity;

import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/communities", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserToCommunityController {
    @Autowired
    private UserToCommunityService userToCommunityService;

    @PostMapping
    public ResponseEntity<UserToCommunity> add(@RequestBody UserToCommunity userToCommunity) {
        UserToCommunity created = userToCommunityService.add(userToCommunity);
        return ResponseEntity.status(201).body(created);  // Retourne un code HTTP 201 pour création
    }

    @PutMapping
    public ResponseEntity<UserToCommunity> update(@RequestBody UserToCommunity userToCommunity) {
        UserToCommunity updated = userToCommunityService.update(userToCommunity);
        return ResponseEntity.ok(updated);  // Retourne le résultat avec un code HTTP 200
    }

    @GetMapping
    public ResponseEntity<List<UserToCommunity>> getAll() {
        List<UserToCommunity> list = userToCommunityService.getAll();
        return ResponseEntity.ok(list);  // Retourne la liste des éléments avec un code HTTP 200
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<List<User>> getAllByCommunity(@PathVariable String communityId) {
        List<User> users = userToCommunityService.getAllByCommunity(communityId);
        return ResponseEntity.ok(users);  // Retourne la liste des utilisateurs pour la communauté spécifiée
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommunityDto>> getAllByUser(@PathVariable Long userId) {
        List<CommunityDto> communities = userToCommunityService.getAllByUser(userId);
        return ResponseEntity.ok(communities);  // Retourne la liste des communautés pour l'utilisateur spécifié
    }
}

