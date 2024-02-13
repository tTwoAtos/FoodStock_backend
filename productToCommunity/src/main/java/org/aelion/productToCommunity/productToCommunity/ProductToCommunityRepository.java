package org.aelion.productToCommunity.productToCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface ProductToCommunityRepository extends JpaRepository<ProductToCommunity, String> {

}
