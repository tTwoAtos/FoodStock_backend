package org.myownstock.user.userToCommunity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.myownstock.user.user.User;

@Getter @Setter
@Entity
public class UserToCommunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String communityId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
