package org.aelion.migration.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryDto extends JpaRepository<CategoryDto, Long> {
}
