package org.aelion.Products.categoryToCommunity;

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
@IdClass(CompositeKey.class)
public class CategoryToCommunity {

    @Id
    @Column(nullable = false)
    private String categoryId;

    @Id
    @Column(nullable = false)
    private String communityId;

    @Column(nullable = false)
    private Long preferencies_factor;
}

