package org.aelion.authentication.requests;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequest {
    protected String email;
    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }
}
