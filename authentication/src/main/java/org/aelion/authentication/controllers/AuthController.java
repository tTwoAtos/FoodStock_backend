package org.aelion.authentication.controllers;

import org.aelion.authentication.dto.UserDto;
import org.aelion.authentication.requests.LoginRequest;
import org.aelion.authentication.requests.PasswordForgotRequest;
import org.aelion.authentication.requests.PasswordResetRequest;
import org.aelion.authentication.requests.RegisterRequest;
import org.aelion.authentication.responses.TokenResponse;
import org.aelion.authentication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login route called");

        String token = authService.login(loginRequest);

        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        UserDto user = authService.register(registerRequest);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/password-forgot")
    public ResponseEntity<?> passwordForgot(@RequestBody PasswordForgotRequest passwordForgotRequest) {
        return null;
    }

    @PostMapping("/password-reset")
    public ResponseEntity<?> passwordReset(@RequestBody PasswordResetRequest passwordResetRequest) {
        return null;
    }
}
