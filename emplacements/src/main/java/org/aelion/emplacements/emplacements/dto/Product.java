package org.aelion.emplacements.emplacements.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String EANCode;

    private String name;
    private String thumbnail;

    private Long nbScanned;

    private Long nbAdded;
}
