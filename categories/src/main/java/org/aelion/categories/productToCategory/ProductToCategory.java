package org.aelion.categories.productToCategory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aelion.categories.categoryToCommunity.CategoryToCommunity;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductToCategory.class)
public class ProductToCategory {

    @Id
    @Column(nullable = false)
    private List<String> categoryIds;

    @Id
    @Column(length = 20, nullable = false)
    private String productId;
}
