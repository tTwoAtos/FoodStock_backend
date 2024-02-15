package org.aelion.categories.productToCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductToCategoryRepository extends JpaRepository<ProductToCategory, String> {
    List<ProductToCategory> findByProductId(String productId);
}
