package org.myownstock.user.user;

import org.myownstock.user.helpers.services.Service;
import org.myownstock.user.user.dto.UserAddRequestDto;
import org.myownstock.user.user.dto.UserUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {
    /**
     * @param User user
     * @return User
     */
    public User add(User user);
    public User addFromDto(UserAddRequestDto user) throws Exception;

    /**
     *
     * @return User[]
     */
    public List<User> getAll();

    /**
     *
     * @return User
     */
    public Optional<User> get(Long id);

    /**
     * @param id Long
     * @param UserUpdateRequestDto user
     * @return User
     */
    public User update (Long id, User user) throws Exception;
    public User updateFromDto (Long id, UserUpdateRequestDto user) throws Exception;
    public void delete (List<Long> userIds) throws Exception;
}
