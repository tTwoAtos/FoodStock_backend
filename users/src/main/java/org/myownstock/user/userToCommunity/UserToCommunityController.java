package org.myownstock.user.userToCommunity;

import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/communities", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserToCommunityController {
    @Autowired
    private UserToCommunityService userToCommunityService;

    @PostMapping
    public UserToCommunity add(@RequestBody UserToCommunity userToCommunity) {
        return userToCommunityService.add(userToCommunity);
    }


    @PutMapping
    public UserToCommunity update(@RequestBody UserToCommunity userToCommunity) {
        return userToCommunityService.update(userToCommunity);
    }

    @GetMapping
    public List<UserToCommunity> getAll() {
        return userToCommunityService.getAll();
    }

    @GetMapping("/{communityId}")
    public List<User> getAllByCommunity(@PathVariable String communityId) {
        return userToCommunityService.getAllByCommunity(communityId);
    }


    @GetMapping("/user/{userId}")
    public List<CommunityDto> getAllByUser(@PathVariable Long userId) {
        return userToCommunityService.getAllByUser(userId);
    }
}


