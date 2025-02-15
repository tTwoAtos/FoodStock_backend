package org.aelion.communities.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/communities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommunityController {
    @Autowired
    private CommunityService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Community> communities = service.getAll();
            return ResponseEntity.ok(communities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des communautés: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Communauté non trouvée avec l'ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCommunity(@RequestBody Community community) {
        try {
            return service.createCommunity(community);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de la communauté: " + e.getMessage());
        }
    }
}

