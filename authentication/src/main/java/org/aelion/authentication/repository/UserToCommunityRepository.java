package org.aelion.authentication.repository;

import org.aelion.authentication.entity.UserToCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserToCommunityRepository extends JpaRepository<UserToCommunity, Long> {

    List<UserToCommunity> findAllByCommunityId(String communityId);

    @Query(name = "SELECT * FROM user JOIN user_to_community as utoc ON utoc.user_id = user.id WHERE user.id = ?1", nativeQuery = true)
    List<UserToCommunity> getAllByUserId(Long usersId);

    @Query(value = "SELECT count(*) FROM user_to_community as utoc WHERE utoc.community_id = ?1", nativeQuery = true)
    Long countUsers(String communityId);
}
