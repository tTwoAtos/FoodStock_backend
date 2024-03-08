package org.myownstock.user.user.Impl;

import jakarta.transaction.Transactional;
import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.roles.IRole;
import org.myownstock.user.user.IUser;
import org.myownstock.user.user.User;
import org.myownstock.user.user.UserService;
import org.myownstock.user.user.dto.UserAddRequestDto;
import org.myownstock.user.user.dto.UserGetRequestDto;
import org.myownstock.user.user.dto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private IUser repository;
    private final static String COMMUNITY_API = "http://COMMUNITY-SERVICE/api/v1/communities";
    @Autowired
    private IRole roleRepo;

    @Override
    @Transactional
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public User addFromDto(UserAddRequestDto user) throws Exception {
        var role = roleRepo.findById(user.getRole());

        if(role.isPresent()) {
            var newUser = new User();
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setGender(user.getGender());
            newUser.setBirthdate(user.getBirthdate());
            newUser.setRole(role.get());

            return repository.save(newUser);
        }

        throw new Exception("Role with : " + user.getRole() + "was not found");
    }

    @Override
    @Transactional
    public User updateFromDto(Long id, UserUpdateRequestDto user) throws Exception {
        var role = roleRepo.findById(user.getRole());
        var usr = repository.findById(id);

        if(role.isPresent() && usr.isPresent()) {
            var newUser = new User();
            newUser.setId(id);
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setGender(user.getGender());
            newUser.setEmail(user.getEmail());
            newUser.setLoggedInCommunityId(user.getLoggedInCommunityId());
            newUser.setBirthdate(user.getBirthdate());
            newUser.setRole(role.get());

            return repository.save(newUser);
        }

        throw new Exception("User was not found");
    }

    @Override
    @Transactional
    public void delete(List<Long> userIds) throws Exception {
        repository.deleteAllById(userIds);
    }

    @Override
    public List<User> getAll(){
        return repository.findAll();
    }

    @Override
    public UserGetRequestDto get(Long id) {
        User user = repository.findById(id).orElseThrow();
        UserGetRequestDto response = new UserGetRequestDto(user);

        if(user.getLoggedInCommunityId() == null)
            return response;

        CommunityDto community = restTemplate.getForObject(COMMUNITY_API + '/' + user.getLoggedInCommunityId(), CommunityDto.class);

        response.setLoggedInCommunity(community);

        return response;
    }

    @Override
    public Optional<User> getLogin(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User update(Long id, User user) throws Exception {
        repository.findById(id).orElseThrow();

        user.setId(id);

        return repository.save(user);
    }


}
