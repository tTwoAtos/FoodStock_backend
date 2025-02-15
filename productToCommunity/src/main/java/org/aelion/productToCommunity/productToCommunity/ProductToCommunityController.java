package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.aelion.productToCommunity.productToCommunity.dto.QuantityDto;
import org.aelion.productToCommunity.productToCommunity.exception.BadRequestException;
import org.aelion.productToCommunity.productToCommunity.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/product-to-community", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductToCommunityController {

    @Autowired
    private ProductToCommunityService service;

    // Récupérer tous les produits avec pagination et filtres
    @GetMapping
    public ResponseEntity<Page<ProductToCommunity>> getAll(
            @RequestParam(defaultValue = "0") int page,  // Numéro de la page (par défaut 0)
            @RequestParam(defaultValue = "10") int size,  // Taille de la page (par défaut 10)
            @RequestParam Map<String, String> filters) {  // Filtres dynamiques

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductToCommunity> productToCommunityPage = (Page<ProductToCommunity>) service.getAll(pageable, filters);
        return new ResponseEntity<>(productToCommunityPage, HttpStatus.OK);
    }

    // Récupérer les produits d'une communauté
    @GetMapping("/{communityId}")
    public Iterable<ProductResponseDto> getAllByCommunity(@PathVariable String communityId) {
        Iterable<ProductResponseDto> products = service.getAllByCommunityId(communityId);
        if (products == null) {
            throw new NotFoundException("Communauté non trouvée avec l'ID " + communityId);
        }
        return products;
    }

    // Récupérer les produits d'une communauté et d'un emplacement donné
    @GetMapping("/{communityId}/{emplacementId}")
    public Iterable<ProductResponseDto> getAllByCommunityByEmplacement(@PathVariable String communityId, @PathVariable String emplacementId) {
        Iterable<ProductResponseDto> products = service.getAllByCommunityIdAndEmplacementId(communityId, emplacementId);
        if (products == null) {
            throw new NotFoundException("Aucun produit trouvé pour la communauté " + communityId + " et l'emplacement " + emplacementId);
        }
        return products;
    }

    // Compter les produits d'une communauté et d'un emplacement donné
    @GetMapping("/{communityId}/{emplacementId}/count")
    public Integer countAllByCommunityByEmplacement(@PathVariable String communityId, @PathVariable String emplacementId) {
        Integer count = service.countAllByCommunityIdAndEmplacementId(communityId, emplacementId);
        if (count == null) {
            throw new NotFoundException("Aucun produit trouvé pour la communauté " + communityId + " et l'emplacement " + emplacementId);
        }
        return count;
    }

    // Ajouter un produit à une communauté
    @PostMapping("/{communityId}")
    public ResponseEntity<?> add(@PathVariable String communityId, @RequestBody ProductToCommunity PtoC) {
        if (PtoC.getProductId() == null || PtoC.getQte() <= 0) {
            throw new BadRequestException("Les informations du produit sont invalides.");
        }
        PtoC.setCommunityId(communityId);
        return service.add(PtoC);
    }

    // Mettre à jour la quantité d'un produit dans une communauté
    @Transactional
    @PutMapping("/{communityId}/{productId}")
    public ResponseEntity<?> updateQuantity(@PathVariable String communityId, @PathVariable String productId, @RequestBody QuantityDto QDto) {
        if (QDto.getQte() <= 0) {
            throw new BadRequestException("La quantité doit être supérieure à 0.");
        }
        return new ResponseEntity<>(service.updateQuantity(communityId, productId, QDto.getQte()), HttpStatus.OK);
    }

    // Supprimer un produit d'une communauté
    @Transactional
    @DeleteMapping("/{communityId}/{productEanCode}")
    public ResponseEntity<?> delete(@PathVariable String productEanCode, @PathVariable String communityId) {
        service.delete(productEanCode, communityId);
        return new ResponseEntity<>("{\"message\":\"Product Deleted\"}", HttpStatus.OK);
    }

    // Supprimer plusieurs produits d'une communauté
    @Transactional
    @DeleteMapping("/{communityId}")
    public ResponseEntity<?> massDelete(@RequestBody List<String> codes, @PathVariable String communityId) {
        if (codes.isEmpty()) {
            throw new BadRequestException("La liste des codes produits ne peut pas être vide.");
        }
        service.massDelete(codes, communityId);
        return new ResponseEntity<>("{\"message\":\"Products Deleted\"}", HttpStatus.OK);
    }
}

