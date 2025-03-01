package org.myownstock.user.user;

import org.aelion.exception.BadRequestException;
import org.myownstock.user.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    // Ajouter un utilisateur
    @PostMapping
    public User add(@RequestBody UserAddRequestDto user) throws Exception {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.addFromDto(user);
    }


    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserUpdateRequestDto user) throws Exception {
        return userService.updateFromDto(id, user);
    }

    @PutMapping("/community/login")
    public boolean communityLogin(@PathVariable Long id, @RequestBody UserUpdateLoggedInCommunityRequestDto request) throws Exception {
        return userService.communityLogin(request);
    }

    // Récupérer un utilisateur par email
    @GetMapping("/{email}/email")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    // Supprimer des utilisateurs
    @DeleteMapping
    public void delete(@RequestBody List<Long> userIds) throws Exception {
        userService.delete(userIds);
    }


    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public UserGetRequestDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    //  Récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAll() {
        throw new BadRequestException("test");
//        return userService.getAll();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto user) throws Exception {
        Optional<User> usr = userService.getLogin(user.getEmail());

        if (usr.isPresent()) {
            Boolean isPasswordValid = new BCryptPasswordEncoder().matches(user.getPassword(), usr.get().getPassword());

            if (isPasswordValid)
                return new ResponseEntity<>(get(usr.get().getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>("\"message\":\"Wrong logins !\"", HttpStatus.NOT_FOUND);
    }
}

