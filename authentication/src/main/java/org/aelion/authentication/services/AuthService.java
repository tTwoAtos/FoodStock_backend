package org.aelion.authentication.services;

import org.aelion.authentication.dto.UserDto;
import org.aelion.authentication.entity.AuthUserEntity;
import org.aelion.authentication.entity.RoleEntity;
import org.aelion.authentication.exception.AuthException;
import org.aelion.authentication.repository.AuthUserRepository;
import org.aelion.authentication.repository.RoleRepository;
import org.aelion.authentication.requests.LoginRequest;
import org.aelion.authentication.requests.RegisterRequest;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Map<String, Object> login(LoginRequest loginRequest) throws AuthException {
        Map<String, Object> response = new HashMap<>();
        AuthUserEntity user = authUserRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new AuthException("User Not Found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String accessToken = tokenService.generate(user, authentication, 3600L);

        response.put("access_token", accessToken);
        response.put("expire_in", 3600L);
        return response;
    }

    public AuthUserEntity register(RegisterRequest registerRequest) throws AuthException {
        Optional<AuthUserEntity> existingUser = authUserRepository.findByEmail(registerRequest.getEmail());
        AuthUserEntity user = new AuthUserEntity(registerRequest);

        if (existingUser.isPresent()) throw  new AuthException("Username already exist");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRoleBySlug(user, "ROLE_USER");

        // TODO: create first community and set loggedInCommunityId

        authUserRepository.save(user);

        return user;
    }

    public String passwordForgot() {
        return null;
    }

    public String passwordReset() {
        return null;
    }

    public void setRoleBySlug(AuthUserEntity user, String roleSlug) {
        RoleEntity role = roleRepository.findBySlug(roleSlug).orElseThrow();
        user.setRole(role);
    }
}
