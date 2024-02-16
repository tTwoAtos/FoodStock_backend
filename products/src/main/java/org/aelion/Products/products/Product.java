package org.aelion.Products.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String EANCode;

    @Column
    private String name;

    @Column(nullable = false)
    private Long nbScanned;

    @Column(nullable = false)
    private Long nbAdded;

    @Column
    private String thumbnail;
}
