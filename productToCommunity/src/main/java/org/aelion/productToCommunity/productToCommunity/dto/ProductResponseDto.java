package org.aelion.productToCommunity.productToCommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private String EANCode;

    private String name;

    private Long nbScanned;
    private String thumbnail;

    private Long nbAdded;
    private Long quantity;
}
