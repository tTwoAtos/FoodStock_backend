package org.myownstock.user.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.roles.Role;
import org.myownstock.user.user.User;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class UserGetRequestDto {
    public UserGetRequestDto(User user){
        this.id = user.getId();
        this.lastname = user.getLastname();
        this.firstname = user.getFirstname();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
    private Long id;

    private String lastname;

    private String firstname;

    private LocalDate birthdate;

    private Integer gender;

    private String email;

    private CommunityDto loggedInCommunity;

    private Role role;
}
