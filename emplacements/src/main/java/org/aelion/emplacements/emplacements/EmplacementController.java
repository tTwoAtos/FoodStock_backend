package org.aelion.emplacements.emplacements;

import jakarta.transaction.Transactional;
import org.aelion.emplacements.emplacements.dto.DeleteEmplacementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/emplacement", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmplacementController {
    @Autowired
    private EmplacementService service;

    @GetMapping("/community/{communityId}")
    public List<Emplacement> getAllByCommunity(@PathVariable Integer communityId) {
        return service.getAllByCommunityId(communityId);
    }


    @GetMapping("/{id}")
    public Emplacement getEmplacementById(@PathVariable Long id) {
        return service.getEmplacementById(id);
    }


    @PostMapping
    public void add(@RequestBody Emplacement emplacement) {
        service.add(emplacement.getCommunityId(), emplacement.getName());
    }


    @Transactional
    @DeleteMapping("/{emplacementId}")
    public DeleteEmplacementDto delete(@PathVariable Long emplacementId) {
        service.delete(emplacementId);
        return new DeleteEmplacementDto("Emplacement supprimé avec succès");
    }

}
