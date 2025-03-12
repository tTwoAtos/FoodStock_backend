package org.aelion.emplacements.emplacements.Impl;

import org.aelion.emplacements.emplacements.Emplacement;
import org.aelion.emplacements.emplacements.EmplacementRepository;
import org.aelion.emplacements.emplacements.EmplacementService;
import org.aelion.emplacements.emplacements.dto.EmplacementListResponse;
import org.aelion.exception.BadRequestException;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmplacementServiceImpl implements EmplacementService {
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";
    private final static String PRODUCT_TO_COMMUNITY_API = "http://PRODUCT-TO-COMMUNITY-SERVICE/api/v1/product-to-community";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmplacementRepository repository;

    @Override
    public List<EmplacementListResponse> getAllByCommunityId(Integer communityId) {

        List<Emplacement> emplacements = repository.findAllByCommunityId(communityId);

        // foreach emplacement, count nb products
        List<EmplacementListResponse> response = new ArrayList<EmplacementListResponse>();
        emplacements.stream().forEach(emplacement -> {
            Integer nbProducts = restTemplate.getForObject(PRODUCT_TO_COMMUNITY_API + "/" + emplacement.getCommunityId() + "/" + emplacement.getId() + "/count", Integer.class);

            EmplacementListResponse res = new EmplacementListResponse(emplacement);
            res.setNbProducts(nbProducts);
            response.add(res);
        });

        return response;
    }

    @Override
    public Emplacement getEmplacementById(Long id) {
        Optional<Emplacement> optionalEmplacement = repository.findById(id);

        if (optionalEmplacement.isEmpty()) {
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
