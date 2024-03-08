package org.myownstock.user.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserAddRequestDto {
    private String lastname;
    private String firstname;
    private LocalDate birthdate;
    private Integer gender;
    private String email;
    private String loggedInCommunityId;
    private String password;
    private Long role;
}
