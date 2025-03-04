package org.aelion.migration.dto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface RoleRepositoryDto extends JpaRepository<RoleEntityDto, Long> {
    public Optional<RoleEntityDto> findBySlug(String slug);
}
