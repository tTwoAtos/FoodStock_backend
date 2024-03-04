package org.myownstock.user.userToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserToCommunity extends JpaRepository<UserToCommunity, Long> {

    List<UserToCommunity> findAllByCommunityId(String communityId);

}
