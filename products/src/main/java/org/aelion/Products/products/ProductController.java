package org.aelion.Products.products;

import org.aelion.Products.products.common.exception.NotFoundException;
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
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(defaultValue = "0") int page, // Page number, default is 0
            @RequestParam(defaultValue = "10") int size, // Page size, default is 10
            @RequestParam Map<String, String> filters) { // Filtres de recherche dynamiques
        try {
            // Création d'un Pageable pour la pagination
            Pageable pageable = PageRequest.of(page, size);

            // Récupérer les produits avec pagination et filtres
            List<Product> products = service.getAll(pageable, filters);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            // Gérer l'exception en cas de problème général (500)
            throw new RuntimeException("Erreur interne lors de la récupération des produits", ex);
        }
    }

    // Récupérer plusieurs produits par une liste d'ID
    @PostMapping("/list")
    public ResponseEntity<List<Product>> getAllByIdList(@RequestBody List<String> productIds) {
        try {
            List<Product> products = service.getAllByIdList(productIds);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            // Gérer l'exception en cas de problème général (500)
            throw new RuntimeException("Erreur interne lors de la récupération des produits", ex);
        }
    }

    // Récupérer un produit par son code
    @GetMapping("/{code}")
    public ResponseEntity<?> getById(@PathVariable String code) {
        try {
            return service.getById(code);
        } catch (NotFoundException ex) {
            // Si le produit n'est pas trouvé (404)
            throw new NotFoundException("Le produit avec le code " + code + " n'a pas été trouvé.");
        } catch (Exception ex) {
            // Gérer l'exception en cas de problème général (500)
            throw new RuntimeException("Erreur interne lors de la récupération du produit", ex);
        }
    }

    // Vérifier si un produit a été ajouté à une communauté
    @PostMapping("/addedToCommunity/{code}")
    public ResponseEntity<?> addedToCommunity(@PathVariable String code) {
        try {
            return service.addedToCommunity(code);
        } catch (SecurityException ex) {
            // Gestion de l'exception d'accès refusé (401)
            throw new SecurityException("Accès refusé lors de l'ajout du produit à la communauté " + code);
        } catch (Exception ex) {
            // Gérer l'exception en cas de problème général (500)
            throw new RuntimeException("Erreur interne lors de l'ajout du produit à la communauté", ex);
        }
    }
}
