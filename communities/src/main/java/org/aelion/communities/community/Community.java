package org.aelion.communities.community;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "communities")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Community {
    @Id
    private String id;

    private String name;

    private String cityCode;
}
