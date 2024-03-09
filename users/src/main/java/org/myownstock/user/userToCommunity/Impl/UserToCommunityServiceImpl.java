package org.myownstock.user.userToCommunity.Impl;

import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.user.User;
import org.myownstock.user.user.UserService;
import org.myownstock.user.userToCommunity.IUserToCommunity;
import org.myownstock.user.userToCommunity.UserToCommunity;
import org.myownstock.user.userToCommunity.UserToCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserToCommunityServiceImpl implements UserToCommunityService {
    @Autowired
    private IUserToCommunity repository;
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Override
    public UserToCommunity add(UserToCommunity userToCom) {
        return repository.save(userToCom);
    }

    @Override
    public List<UserToCommunity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getAllByCommunity(String communityId) {
       List<UserToCommunity> uToCs = repository.findAllByCommunityId(communityId);

       return uToCs.stream().map((uToC) -> uToC.getUser()).toList();
    }
    @Override
    public List<CommunityDto> getAllByUser(Long userId) {
        List<UserToCommunity> uToCs = repository.getAllByUserId(userId);
        List<CommunityDto> res = new ArrayList<>();

        for (UserToCommunity uToC:uToCs){
            CommunityDto community = restTemplate.getForObject(COMMUNITY_API + '/' + uToC.getCommunityId(), CommunityDto.class);
            res.add(community);
        }

        return res;
    }
    @Override
    public Optional<UserToCommunity> get(Long uToCId) {
        return repository.findById(uToCId);
    }

    @Override
    public UserToCommunity update(UserToCommunity userToCom) {
        return repository.save(userToCom);
    }
}