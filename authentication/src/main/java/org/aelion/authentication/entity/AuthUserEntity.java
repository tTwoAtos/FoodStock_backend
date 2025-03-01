package org.aelion.authentication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aelion.authentication.requests.RegisterRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
@DynamicUpdate
@NoArgsConstructor
public class AuthUserEntity {
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
    private Integer loggedInCommunityId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    public AuthUserEntity(RegisterRequest registerRequest) {
        this.lastname = registerRequest.getLastname();
        this.firstname = registerRequest.getFirstname();
        this.email = registerRequest.getEmail();
        this.gender = registerRequest.getGender();
        this.password = registerRequest.getPassword();
    }
}
