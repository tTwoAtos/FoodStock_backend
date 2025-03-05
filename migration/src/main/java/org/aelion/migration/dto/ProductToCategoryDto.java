package org.aelion.migration.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product_to_category")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductToCategoryCompositeKey.class)
public class ProductToCategoryDto {
    @Id
    @Column(nullable = false)
    private Long categoryId;

    @Id
    @Column(length = 20, nullable = false)
    private String productId;
}
