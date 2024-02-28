package org.aelion.productToCommunity.productToCommunity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String EANCode;

    private String name;
    private String thumbnail;

    private Long nbScanned;

    private Long nbAdded;
}
