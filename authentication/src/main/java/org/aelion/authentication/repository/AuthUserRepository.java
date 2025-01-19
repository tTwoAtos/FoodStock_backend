package org.aelion.authentication.repository;

import jakarta.transaction.Transactional;
import org.aelion.authentication.entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long> {
    public Optional<AuthUserEntity> findByEmail(String email);
}
