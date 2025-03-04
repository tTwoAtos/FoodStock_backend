package org.aelion.migration.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "emplacement")
@AllArgsConstructor
@NoArgsConstructor
public class EmplacementDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer communityId;

    @Column(nullable = false)
    private String name;

}