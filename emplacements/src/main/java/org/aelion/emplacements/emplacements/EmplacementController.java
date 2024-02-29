package org.aelion.emplacements.emplacements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emplacement")
public class EmplacementController {
    @Autowired
    private EmplacementService service;

    @GetMapping("/community/{communityId}")
    public Iterable<Emplacement> getAllByCommunity(@PathVariable String communityId){
        return  service.getAllByCommunityId(communityId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmplacementById(@PathVariable Long id){
        return  service.getEmplacementById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody String communityId ,String name){
        return  service.add(communityId ,name);
    }

    @Transactional
    @DeleteMapping("/delete/{emplacementId}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Emplacement Deleted", HttpStatus.OK);
    }
}
