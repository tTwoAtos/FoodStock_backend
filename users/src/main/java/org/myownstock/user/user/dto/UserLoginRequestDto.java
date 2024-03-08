package org.myownstock.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myownstock.user.user.User;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserLoginRequestDto {
    public UserLoginRequestDto(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    private String email;
    private String password;

}
