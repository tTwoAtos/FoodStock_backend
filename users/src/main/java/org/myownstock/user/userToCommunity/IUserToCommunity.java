package org.myownstock.user.userToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserToCommunity extends JpaRepository<UserToCommunity, Long> {

    List<UserToCommunity> findAllByCommunityId(String communityId);
    @Query(name = "SELECT * FROM user JOIN user_to_community as utoc ON utoc.user_id = user.id WHERE user.id = ?1", nativeQuery = true)
    List<UserToCommunity> getAllByUserId(Long userId);
}
