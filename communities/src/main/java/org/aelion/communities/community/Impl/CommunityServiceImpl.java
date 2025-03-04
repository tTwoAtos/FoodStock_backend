package org.aelion.communities.community.Impl;

import org.aelion.communities.community.Community;
import org.aelion.communities.community.CommunityRepository;
import org.aelion.communities.community.CommunityService;
import org.aelion.communities.community.dto.City;
import org.aelion.communities.community.dto.CommunityResponse;
import org.aelion.exception.BadRequestException;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    private final static String CITY_API = "http://CITY-SERVICE/api/v1/cities";
    @Autowired
    private CommunityRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Community> getAll() {
        return repository.findAll();
    }

    @Override
    public CommunityResponse getById(Integer id) {
        Community community = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Communauté non trouvée avec l'ID : " + id));

        City city = restTemplate.getForObject(CITY_API + '/' + community.getCityCode(), City.class);

        CommunityResponse cr = new CommunityResponse();
        cr.setId(community.getId());
        cr.setName(community.getName());
        cr.setCity(city);

        return cr;
    }


    @Override
    public Community createCommunity(Community community) {
        if (community == null || community.getName() == null || community.getName().trim().isEmpty()) {
            throw new BadRequestException("Les informations de la communauté sont invalides.");
        }

        return repository.save(community);
    }


}
