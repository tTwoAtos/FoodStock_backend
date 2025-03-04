package org.aelion.migration.dto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepositoryDto extends JpaRepository<ProductDto, String>, JpaSpecificationExecutor<ProductDto> {
    @Query(value = "SELECT * FROM product WHERE eancode IN (:productIds) ORDER BY eancode", nativeQuery = true)
    List<ProductDto> findByIdList(List<String> productIds);
}
