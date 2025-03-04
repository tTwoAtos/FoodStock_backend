package org.aelion.migration.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
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
