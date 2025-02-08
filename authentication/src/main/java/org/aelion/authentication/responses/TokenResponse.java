package org.aelion.authentication.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter @Setter
public class TokenResponse {
    private String access_token;
    private Long expire_in;

    public TokenResponse(Map<String,Object> access_token) {
        this.access_token = (String) access_token.get("access_token");
        this.expire_in = (Long) access_token.get("expire_in");
    }
}
