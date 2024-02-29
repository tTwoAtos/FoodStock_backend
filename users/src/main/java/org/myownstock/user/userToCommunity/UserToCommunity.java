package org.myownstock.user.userToCommunity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.myownstock.user.user.User;

@Getter @Setter
@Entity
@Table(name = "user_to_community")
public class UserToCommunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String communityId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
