package org.aelion.Products.categoryToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryToCommunityRepository extends JpaRepository<CategoryToCommunity, String> {

}
