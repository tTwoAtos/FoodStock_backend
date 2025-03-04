package org.aelion.migration.dto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface UserRepositoryDto extends JpaRepository<UserEntityDto, Long> {
    public Optional<UserEntityDto> findByEmail(String email);
}
