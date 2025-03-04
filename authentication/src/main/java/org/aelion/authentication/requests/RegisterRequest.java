package org.aelion.authentication.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
public class RegisterRequest {
    private String lastname;
    private String firstname;
    private Integer gender;
    private String email;
    private String password;
    private Optional<String> invitation_code;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", invitation_code='" + invitation_code + '\'' +
                '}';
    }
}
