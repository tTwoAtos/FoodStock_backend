package org.aelion.authentication.repository;

import jakarta.transaction.Transactional;
import org.aelion.authentication.entity.AuthUserEntity;
import org.aelion.authentication.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public Optional<RoleEntity> findBySlug(String slug);
}
