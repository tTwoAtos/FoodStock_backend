package org.aelion.migration.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToCategoryRepositoryDto extends JpaRepository<ProductToCategoryDto, String> {
//    List<ProductToCategoryDto> findByProductId(String productId);
//
//    List<ProductToCategoryDto> findByCategoryId(Long categoryId);
}
