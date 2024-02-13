package org.aelion.productToCommunity.productToCommunity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String EANCode;

    private String name;

    private Long nbScanned;

    private Long nbAdded;
}
