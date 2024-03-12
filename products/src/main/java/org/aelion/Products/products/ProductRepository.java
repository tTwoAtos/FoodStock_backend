package org.aelion.Products.products;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM product WHERE eancode IN (:productIds) ORDER BY eancode", nativeQuery = true)
    List<Product> findByIdList(List<String> productIds);
}
