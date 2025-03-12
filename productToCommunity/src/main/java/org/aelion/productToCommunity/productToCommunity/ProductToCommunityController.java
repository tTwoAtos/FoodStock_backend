package org.aelion.productToCommunity.productToCommunity;

import jakarta.transaction.Transactional;
import org.aelion.exception.BadRequestException;
import org.aelion.exception.NotFoundException;
import org.aelion.productToCommunity.productToCommunity.dto.ProductResponseDto;
import org.aelion.productToCommunity.productToCommunity.dto.QuantityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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
    public Page<ProductToCommunity> getAll(
            @RequestParam(defaultValue = "0") int page,  // Numéro de la page (par défaut 0)
            @RequestParam(defaultValue = "10") int size,  // Taille de la page (par défaut 10)
            @RequestParam Map<String, String> filters) {  // Filtres dynamiques

        Pageable pageable = PageRequest.of(page, size);
        return (Page<ProductToCommunity>) service.getAll(pageable, filters);
    }


    // Récupérer les produits d'une communauté
    @GetMapping("/{communityId}")
    public List<ProductResponseDto> getAllByCommunity(@PathVariable Integer communityId) {
        List<ProductResponseDto> products = service.getAllByCommunityId(communityId);
        if (products == null) {
            throw new NotFoundException("Communauté non trouvée avec l'ID " + communityId);
        }
        return products;
    }


    // Récupérer les produits d'une communauté et d'un emplacement donné
    @GetMapping("/{communityId}/{emplacementId}")
    public List<ProductResponseDto> getAllByCommunityByEmplacement(@PathVariable Integer communityId, @PathVariable Integer emplacementId) {
        List<ProductResponseDto> products = service.getAllByCommunityIdAndEmplacementId(communityId, emplacementId);
        if (products == null) {
            throw new NotFoundException("Aucun produit trouvé pour la communauté " + communityId + " et l'emplacement " + emplacementId);
        }
        return products;
    }

    // Compter les produits d'une communauté et d'un emplacement donné
    @GetMapping("/{communityId}/{emplacementId}/count")
    public Integer countAllByCommunityByEmplacement(@PathVariable Integer communityId, @PathVariable Integer emplacementId) {
        Integer count = service.countAllByCommunityIdAndEmplacementId(communityId, emplacementId);
        if (count == null) {
            throw new NotFoundException("Aucun produit trouvé pour la communauté " + communityId + " et l'emplacement " + emplacementId);
        }
        return count;
    }


    // Ajouter un produit à une communauté
    @PostMapping("/{communityId}")
    public ProductToCommunity add(@PathVariable Integer communityId, @RequestBody ProductToCommunity PtoC) {
        if (PtoC.getProductId() == null || PtoC.getQte() <= 0) {
            throw new BadRequestException("Les informations du produit sont invalides.");
        }
        PtoC.setCommunityId(communityId);
        return service.add(PtoC);
    }


    // Mettre à jour la quantité d'un produit dans une communauté
    @Transactional
    @PutMapping("/{communityId}/{productId}")
    public ProductToCommunity updateQuantity(@PathVariable Integer communityId, @PathVariable String productId, @RequestBody QuantityDto QDto) {
        if (QDto.getQte() < 0) {
            throw new BadRequestException("La quantité doit être supérieure à 0.");
        }
        return service.updateQuantity(communityId, productId, QDto.getQte());
    }


    // Supprimer un produit d'une communauté
    @Transactional
    @DeleteMapping("/{communityId}/{productEanCode}")
    public String delete(@PathVariable String productEanCode, @PathVariable Integer communityId) {
        service.delete(productEanCode, communityId);
        return "{\"message\":\"Product Deleted\"}";
    }


    // Supprimer plusieurs produits d'une communauté
    @Transactional
    @DeleteMapping("/{communityId}")
    public String massDelete(@RequestBody List<String> codes, @PathVariable Integer communityId) {
        if (codes.isEmpty()) {
            throw new BadRequestException("La liste des codes produits ne peut pas être vide.");
        }
        service.massDelete(codes, communityId);
        return "{\"message\":\"Products Deleted\"}";
    }

}

