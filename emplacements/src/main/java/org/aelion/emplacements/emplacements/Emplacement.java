package org.aelion.emplacements.emplacements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Emplacement {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String communityId;

    @Column(nullable = false)
    private String name;

}