package org.aelion.migration.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "city")
@Getter
@Setter
public class CityDto {
    @Id
    private String inseeCode;

    @Column(length = 5)
    private String postalCode;

    @Column(length = 75)
    private String name;
}
