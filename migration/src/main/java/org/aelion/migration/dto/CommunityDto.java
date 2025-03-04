package org.aelion.migration.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "community")
@Getter
@Setter
@NoArgsConstructor
public class CommunityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(name = "insee_code", length = 75)
    private String cityCode;
}
