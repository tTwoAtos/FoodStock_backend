package org.aelion.communities.community.Impl;

import org.aelion.communities.community.Community;
import org.aelion.communities.community.CommunityRepository;
import org.aelion.communities.community.CommunityService;
import org.aelion.communities.community.dto.City;
import org.aelion.communities.community.dto.CommunityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getById(String id) {
        Optional<Community> optionalCommunity = repository.findById(id);

        if (optionalCommunity.isPresent()) {
            City city = restTemplate.getForObject(CITY_API + '/' + optionalCommunity.get().getCityCode(), City.class);

            CommunityResponse cr = new CommunityResponse();
            cr.setId(optionalCommunity.get().getId());
            cr.setName(optionalCommunity.get().getName());
            cr.setCity(city);

            return new ResponseEntity<>(cr, HttpStatus.OK);
        }

        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> createCommunity(Community community) {
        return new ResponseEntity<Community>(repository.save(community), HttpStatus.CREATED);
    }
}
