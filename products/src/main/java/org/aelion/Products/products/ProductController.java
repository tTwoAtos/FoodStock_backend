package org.aelion.Products.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService service;

    // Récupérer tous les produits avec pagination et filtres
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(
            @RequestParam(defaultValue = "0") int page, // Page number, default is 0
            @RequestParam(defaultValue = "10") int size, // Page size, default is 10
            @RequestParam Map<String, String> filters) { // Filtres de recherche dynamiques
        Pageable pageable = PageRequest.of(page, size);
        return service.getAll(pageable, filters);
    }


    // Récupérer plusieurs produits par une liste d'ID
    @PostMapping("/list")
    public List<Product> getAllByIdList(@RequestBody List<String> productIds) {
        return service.getAllByIdList(productIds);
    }


    // Récupérer un produit par son code
    @GetMapping("/{code}")
    public ResponseEntity<?> getById(@PathVariable String code) {
        return service.getById(code);
    }


    // Vérifier si un produit a été ajouté à une communauté
    @PostMapping("/addedToCommunity/{code}")
    public void addedToCommunity(@PathVariable String code) {
        service.addedToCommunity(code);
    }

}
