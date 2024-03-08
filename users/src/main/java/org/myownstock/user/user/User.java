package org.myownstock.user.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.myownstock.user.roles.Role;

import java.time.LocalDate;

@Getter @Setter
@Entity
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String lastname;

    @Column(length = 75)
    private String firstname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private Integer gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String loggedInCommunityId;

    @ManyToOne(optional = false)
    private Role role;
}
