package org.myownstock.user.userToCommunity;

import org.myownstock.user.helpers.services.Service;

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
    public Optional<UserToCommunity> get(Long uToCId);

    /**
     * @param UserToCommunity userToCom
     * @return UserToCommunity[]
     */
    public UserToCommunity update(UserToCommunity userToCom);
}
