package org.aelion.emplacements.emplacements;

import org.aelion.emplacements.emplacements.dto.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emplacement")
public class EmplacementController {

    @Autowired
    private EmplacementService service;

    @GetMapping("/{communityId}")
    public Iterable<Emplacement> getAllByCommunity(@PathVariable String communityId){
        return  service.getAllByCommunityId(communityId);
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<?> getEmplacementById(@PathVariable String id){
        return  service.getEmplacementById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody String communityId ,String name){
        return  service.add(communityId ,name);
    }
}
