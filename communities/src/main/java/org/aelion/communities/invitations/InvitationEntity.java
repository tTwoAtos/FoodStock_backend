package org.aelion.communities.invitations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aelion.communities.community.Community;
import org.aelion.communities.invitations.dto.UserEntityDto;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity(name = "invitations")
@DynamicUpdate
@NoArgsConstructor
public class InvitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: make the combination of community and email unique
    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(optional = false)
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private UserEntityDto user;

    @Column(length = 50, nullable = false, unique = true)
    private String code;

    public InvitationEntity(Community community, UserEntityDto user, String code) {
        this.community = community;
        this.user = user;
        this.code = code;
    }
}
