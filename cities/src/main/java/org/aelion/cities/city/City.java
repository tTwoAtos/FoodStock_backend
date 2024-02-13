package org.aelion.cities.city;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class City {
    @Id
    private String inseeCode;

    @Column(length = 5)
    private String postalCode;

    @Column(length = 75)
    private String name;
}
