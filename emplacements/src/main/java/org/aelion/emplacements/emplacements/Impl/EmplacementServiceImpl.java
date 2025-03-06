package org.aelion.emplacements.emplacements.Impl;

import org.aelion.exception.BadRequestException;
import org.aelion.exception.NotFoundException;
import org.aelion.emplacements.emplacements.Emplacement;
import org.aelion.emplacements.emplacements.EmplacementRepository;
import org.aelion.emplacements.emplacements.EmplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmplacementServiceImpl implements EmplacementService {
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";

    @Autowired
    private EmplacementRepository repository;

    @Override
    public List<Emplacement> getAllByCommunityId(Integer communityId) {

        return repository.findAllByCommunityId(communityId);
    }

    @Override
    public Emplacement getEmplacementById(Long id) {
        Optional<Emplacement> optionalEmplacement = repository.findById(id);

        if(optionalEmplacement.isEmpty()){
            throw new NotFoundException("Non trouvé");
        }

        Emplacement emplacement = new Emplacement(
                optionalEmplacement.get().getId(),
                optionalEmplacement.get().getCommunityId(),
                optionalEmplacement.get().getName()
        );

        return emplacement;
    }


    @Override
    public void add(Integer communityId, String name) {
        if (communityId == null || name == null || name.isEmpty()) {
            throw new BadRequestException("Le communityId ou le nom ne peuvent pas être vides.");
        }

        Emplacement emplacement = new Emplacement();
        emplacement.setCommunityId(communityId);
        emplacement.setName(name);

        emplacement = repository.save(emplacement);

    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
