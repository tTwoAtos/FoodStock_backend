package org.aelion.communities.invitations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, Long> {
    List<InvitationEntity> findAllByCommunityId(Integer communityId);

    Optional<InvitationEntity> findByCode(String code);

    Optional<InvitationEntity> findByCodeAndUserEmail(String code, String email);

    Optional<InvitationEntity> findByCommunityIdAndUserEmail(Integer communityId, String email);
}
