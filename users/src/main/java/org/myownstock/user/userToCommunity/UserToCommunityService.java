package org.myownstock.user.userToCommunity;

import org.myownstock.user.dto.CommunityDto;
import org.myownstock.user.helpers.services.Service;
import org.myownstock.user.user.User;

import java.util.List;
import java.util.Optional;

public interface UserToCommunityService extends Service<UserToCommunity> {
    /**w
     * @param UserToCommunity userToComunity
     * @return UserToCommunity
     */
    public UserToCommunity add(UserToCommunity userToCom);

    /**
     *
     * @return UserToCommunity[]
     */
    public List<UserToCommunity> getAll();

    public List<User> getAllByCommunity(String communityId);
    public List<CommunityDto> getAllByUser(Long userId);

    public Optional<UserToCommunity> get(Long uToCId);

    /**
     * @param UserToCommunity userToCom
     * @return UserToCommunity[]
     */
    public UserToCommunity update(UserToCommunity userToCom);
}
