package org.aelion.migration.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "category")
//@SQLInsert(sql = "INSERT IGNORE INTO category(name,id) VALUES (?,?)")
public class CategoryDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, unique = true)
    private String name;
}
