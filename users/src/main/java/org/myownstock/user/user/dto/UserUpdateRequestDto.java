package org.myownstock.user.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserUpdateRequestDto {
    private String lastname;

    private String firstname;

    private LocalDate birthdate;

    private Integer gender;
    private String email;
    private String loggedInCommunityId;

    private Long role;
}
