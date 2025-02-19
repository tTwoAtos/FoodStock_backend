package org.aelion.Products.products;

import org.aelion.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    // Modification de la méthode getAll pour accepter la pagination et les filtres
    List<Product> getAll(Pageable pageable, Map<String, String> filters);

    List<Product> getAllByIdList(List<String> productIds);

   Product getById(String code) throws NotFoundException;

    void addedToCommunity(String code);

}
