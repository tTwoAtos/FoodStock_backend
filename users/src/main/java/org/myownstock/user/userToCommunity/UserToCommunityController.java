package org.myownstock.user.userToCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{uToCId}")
    public ResponseEntity<?> get(@PathVariable Long uToCId){
        Optional<UserToCommunity> uToC = userToCommunityService.get(uToCId);

        if(uToC.isPresent())
            return new ResponseEntity<>(uToC, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
