package org.myownstock.user.userToCommunity;

import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/communities")
public class UserToCommunityController {
    @Autowired
    UserToCommunityService userToCommunityService;

    @PostMapping
    public UserToCommunity add(@RequestBody UserToCommunity userToComunity){
        return userToCommunityService.add(userToComunity);
    }
    @PutMapping
    public UserToCommunity update(@RequestBody UserToCommunity userToCom){
        return userToCommunityService.update(userToCom);
    }

    @GetMapping
    public List<UserToCommunity> getAll(){
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

//    @GetMapping("/{uToCId}")
//    public ResponseEntity<?> get(@PathVariable Long uToCId){
//        Optional<UserToCommunity> uToC = userToCommunityService.get(uToCId);
//
//        if(uToC.isPresent())
//            return new ResponseEntity<>(uToC, HttpStatus.OK);
//        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
//    }
}
