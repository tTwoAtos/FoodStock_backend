package org.myownstock.user.user;

import org.myownstock.user.user.dto.UserAddRequestDto;
import org.myownstock.user.user.dto.UserGetRequestDto;
import org.myownstock.user.user.dto.UserLoginRequestDto;
import org.myownstock.user.user.dto.UserUpdateRequestDto;
import org.myownstock.user.user.exception.ErrorResponse;
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
    public ResponseEntity<?> add(@RequestBody UserAddRequestDto userDto) {
        try {
            userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            User user = userService.addFromDto(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);  // Retourne un statut 201 pour la création
        } catch (Exception e) {
            // Gestion de l'exception générique avec un message d'erreur
            return new ResponseEntity<>(new ErrorResponse("Erreur lors de la création de l'utilisateur", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateRequestDto user) {
        try {
            User updatedUser = userService.updateFromDto(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            // Gestion des erreurs spécifiques lors de la mise à jour
            return new ResponseEntity<>(new ErrorResponse("Erreur lors de la mise à jour de l'utilisateur", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Récupérer un utilisateur par email
    @GetMapping("/{email}/email")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        try {
            User user = userService.getByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // Si l'utilisateur n'est pas trouvé ou autre erreur
            return new ResponseEntity<>(new ErrorResponse("Utilisateur non trouvé", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer des utilisateurs
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody List<Long> userIds) {
        try {
            userService.delete(userIds);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retourne un statut 204 après suppression
        } catch (Exception e) {
            // Gestion des erreurs de suppression
            return new ResponseEntity<>(new ErrorResponse("Erreur lors de la suppression des utilisateurs", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            UserGetRequestDto user = userService.get(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // Erreur lorsque l'utilisateur n'est pas trouvé
            return new ResponseEntity<>(new ErrorResponse("Utilisateur non trouvé", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Authentification de l'utilisateur (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto user) {
        try {
            Optional<User> usr = userService.getLogin(user.getEmail());

            if (usr.isPresent()) {
                Boolean isPasswordValid = new BCryptPasswordEncoder().matches(user.getPassword(), usr.get().getPassword());

                if (isPasswordValid) {
                    return new ResponseEntity<>(get(usr.get().getId()).getBody(), HttpStatus.OK);
                }
            }

            // Erreur de connexion si l'email ou le mot de passe est incorrect
            return new ResponseEntity<>(new ErrorResponse("Login incorrect", "Vérifiez vos identifiants"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Gestion d'erreur lors de l'authentification
            return new ResponseEntity<>(new ErrorResponse("Erreur lors de la tentative de connexion", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
