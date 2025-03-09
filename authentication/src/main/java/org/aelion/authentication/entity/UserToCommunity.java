package org.aelion.authentication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserToCommunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String communityId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUserEntity user;

    public UserToCommunity(Integer communityId, AuthUserEntity user) {
        this.communityId = String.valueOf(communityId);
        this.user = user;
    }
}
