package org.aelion.authentication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aelion.authentication.dto.RoleDto;
import org.aelion.authentication.repository.RoleRepository;
import org.aelion.authentication.requests.RegisterRequest;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter @Setter
@Entity(name = "user")
@DynamicUpdate
public class AuthUserEntity {
    public AuthUserEntity(){}

    public AuthUserEntity(RegisterRequest registerRequest) {
        this.lastname = registerRequest.getLastname();
        this.firstname = registerRequest.getFirstname();
        this.email = registerRequest.getEmail();
        this.gender = registerRequest.getGender();
        this.password = registerRequest.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String lastname;

    @Column(length = 75)
    private String firstname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column()
    private LocalDate birthdate;

    @Column(nullable = false)
    private Integer gender;

    @Column
    private String loggedInCommunityId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
