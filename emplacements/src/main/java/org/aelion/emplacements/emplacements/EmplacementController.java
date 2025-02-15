package org.aelion.emplacements.emplacements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/emplacement", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmplacementController {
    @Autowired
    private EmplacementService service;

    @GetMapping("/community/{communityId}")
    public ResponseEntity<?> getAllByCommunity(@PathVariable String communityId) {
        try {
            List<Emplacement> emplacements = service.getAllByCommunityId(communityId);
            return ResponseEntity.ok(emplacements);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des emplacements pour la communauté: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmplacementById(@PathVariable Long id) {
        try {
            return service.getEmplacementById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Emplacement non trouvé avec l'ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Emplacement emplacement) {
        try {
            return service.add(emplacement.getCommunityId(), emplacement.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de l'emplacement: " + e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/{emplacementId}")
    public ResponseEntity<?> delete(@PathVariable Long emplacementId) {
        try {
            service.delete(emplacementId);
            return ResponseEntity.ok("{\"message\":\"Emplacement supprimé avec succès\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'emplacement: " + e.getMessage());
        }
    }
}
