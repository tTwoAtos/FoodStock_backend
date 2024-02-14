package org.aelion.categories.productToCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToCategoryRepository extends JpaRepository<ProductToCategory, String> {

}
