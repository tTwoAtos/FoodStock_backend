package org.aelion.emplacements.emplacements.Impl;

import org.aelion.emplacements.emplacements.Emplacement;
import org.aelion.emplacements.emplacements.EmplacementRepository;
import org.aelion.emplacements.emplacements.EmplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmplacementServiceImpl implements EmplacementService {
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";

    @Autowired
    private EmplacementRepository repository;

    @Override
    public List<Emplacement> getAllByCommunityId(String communityId) {

        return repository.findAllByCommunityId(communityId);
    }

    @Override
    public ResponseEntity<?> getEmplacementById(Long id) {
        Optional<Emplacement> optionalEmplacement = repository.findById(id);

        if(optionalEmplacement.isPresent()){
            Emplacement emplacement = new Emplacement(
                    optionalEmplacement.get().getId(),
                    optionalEmplacement.get().getCommunityId(),
                    optionalEmplacement.get().getName()
            );

            return new ResponseEntity<>(emplacement, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(String communityId, String name) {
        Emplacement emplacement = new Emplacement();
        emplacement.setCommunityId(communityId);
        emplacement.setName(name);

        repository.save(emplacement);

        return new ResponseEntity<>("{\"message\":\"OK\"}", HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
