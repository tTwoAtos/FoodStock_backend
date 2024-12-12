package org.aelion.authentication.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RegisterRequest {
    private String lastname;
    private String firstname;
    private LocalDate birthdate;
    private Integer gender;
    private String email;
    private String loggedInCommunityId;
    private String password;
    private Long role_id;
}
