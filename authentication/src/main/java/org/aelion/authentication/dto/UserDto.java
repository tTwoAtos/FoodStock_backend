package org.aelion.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String lastname;

    private String firstname;

    private LocalDate birthdate;

    private Integer gender;

    private String email;

    private String password;

    private String loggedInCommunityId;

    private RoleDto role;

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate=" + birthdate
                + ", gender=" + gender + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }

}
