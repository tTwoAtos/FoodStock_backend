package org.aelion.pubs.pubs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class ProductDto {
    private String EANCode;
    private String name;
    private Long nbScanned;
    private Long nbAdded;
    private String thumbnail;
}
