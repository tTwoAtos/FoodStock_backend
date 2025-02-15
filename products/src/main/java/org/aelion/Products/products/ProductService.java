package org.aelion.Products.products;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    // Modification de la méthode getAll pour accepter la pagination et les filtres
    List<Product> getAll(Pageable pageable, Map<String, String> filters);

    List<Product> getAllByIdList(List<String> productIds);

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> addedToCommunity(String code);

}
