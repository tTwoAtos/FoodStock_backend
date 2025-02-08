package org.aelion.authentication.services;

import org.aelion.authentication.entity.AuthUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String generate(AuthUserEntity user, Authentication auth, long duration) {
        Instant now = Instant.now();

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer("Ornate")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(duration))
                .subject(auth.getName())
                .claim("role", auth.getAuthorities().toString())
                .claim("firstname", user.getFirstname())
                .claim("lastname", user.getLastname())
                .claim("user_id", user.getId());

        if (user.getLoggedInCommunityId() != null) {
            claimsBuilder.claim("logged_in_community_id", user.getLoggedInCommunityId());
        }

        return jwtEncoder.encode(
                JwtEncoderParameters.from(
                        claimsBuilder.build()
                )
        ).getTokenValue();
    }
}