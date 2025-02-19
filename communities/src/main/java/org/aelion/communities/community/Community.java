package org.aelion.communities.community;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "communities")

@Getter @Setter
public class Community {
    @Id
    private String id;

    private String name;

    private String cityCode;
}
