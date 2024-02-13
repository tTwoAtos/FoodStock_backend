package org.aelion.communities.community;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "communities")
@Getter @Setter
public class Community {
    @Id
    private String id;

    private String name;

    private String cityCode;
}
