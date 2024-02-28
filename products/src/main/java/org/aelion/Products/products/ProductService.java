package org.aelion.Products.products;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    List<Product> getAllByIdList(List<String> productIds);

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> addedToCommunity(String code);
}
