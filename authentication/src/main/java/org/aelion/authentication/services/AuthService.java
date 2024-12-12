package org.aelion.authentication.services;

import org.aelion.authentication.dto.UserDto;
import org.aelion.authentication.requests.LoginRequest;
import org.aelion.authentication.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    String usersAPI = "http://USER-SERVICE/api/v1/users";

    @Autowired
    private RestTemplate restTemplate;

    public String login(LoginRequest loginRequest) {
        UserDto user = restTemplate.getForEntity(usersAPI + "/" + loginRequest.getEmail() + "/email", UserDto.class).getBody();

        Boolean isPasswordValid = new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            return null;
        }

        return new TokenService().generateToken(user.getId(), user.getRole().getSlug());
    }

    public UserDto register(RegisterRequest registerRequest) {
        try {
            UserDto user = restTemplate.postForObject(usersAPI, registerRequest, UserDto.class);

            System.out.println(user);

            return user;
        } catch (HttpClientErrorException e) {
            System.out.println("Response Headers: " + e.getResponseHeaders());
            System.out.println("Response Body: " + e.getResponseBodyAsString());
            throw e;
        }

    }

    public String passwordForgot() {
        return null;
    }

    public String passwordReset() {
        return null;
    }
}
