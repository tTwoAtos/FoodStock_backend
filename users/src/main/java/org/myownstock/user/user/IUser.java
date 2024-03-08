package org.myownstock.user.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface IUser extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
