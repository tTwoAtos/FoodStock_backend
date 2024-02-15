package org.aelion.categories.productToCategory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductToCategoryCompositeKey.class)
public class ProductToCategory {

    @Id
    @Column(nullable = false)
    private String categoryId;

    @Id
    @Column(length = 20, nullable = false)
    private String productId;
}
