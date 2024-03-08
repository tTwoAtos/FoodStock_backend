package org.myownstock.user.user;

import org.myownstock.user.user.dto.UserAddRequestDto;
import org.myownstock.user.user.dto.UserGetRequestDto;
import org.myownstock.user.user.dto.UserLoginRequestDto;
import org.myownstock.user.user.dto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserAddRequestDto user) throws Exception {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.addFromDto(user));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserUpdateRequestDto user) throws Exception {
        return userService.updateFromDto(id, user);
    }

    @DeleteMapping
    public ResponseEntity<?> delete( @RequestBody List<Long> userIds) throws Exception {
        userService.delete(userIds);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public UserGetRequestDto get(@PathVariable Long id) throws Exception {
        return userService.get(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto user) throws Exception {
        Optional<User> usr = userService.getLogin(user.getEmail());

        if (usr.isPresent()){
            Boolean isPasswordValid = new BCryptPasswordEncoder().matches(user.getPassword(), usr.get().getPassword());

            if(isPasswordValid)
                return new ResponseEntity<>(get(usr.get().getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>("\"message\":\"Wrong logins !\"", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
}
