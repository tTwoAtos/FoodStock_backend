package org.aelion.migration.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserToCommunityRepositoryDto extends JpaRepository<UserToCommunityDto, Long> {

//    List<UserToCommunityDto> findAllByCommunityId(String communityId);

    UserToCommunityDto findByUserId(Long userId);

    @Query(name = "SELECT * FROM user JOIN user_to_community as utoc ON utoc.user_id = user.id WHERE user.id = ?1", nativeQuery = true)
    List<UserToCommunityDto> getAllByUserId(Long usersId);
}
