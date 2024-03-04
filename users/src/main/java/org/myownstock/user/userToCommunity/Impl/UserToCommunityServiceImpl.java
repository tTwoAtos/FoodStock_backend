package org.myownstock.user.userToCommunity.Impl;

import org.myownstock.user.userToCommunity.IUserToCommunity;
import org.myownstock.user.userToCommunity.UserToCommunity;
import org.myownstock.user.userToCommunity.UserToCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserToCommunityServiceImpl implements UserToCommunityService {
    @Autowired
    private IUserToCommunity repository;

    @Override
    public UserToCommunity add(UserToCommunity userToCom) {
        return repository.save(userToCom);
    }

    @Override
    public List<UserToCommunity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserToCommunity> getAllByCommunity(String communityId) {
       return  repository.findAllByCommunityId(communityId);
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