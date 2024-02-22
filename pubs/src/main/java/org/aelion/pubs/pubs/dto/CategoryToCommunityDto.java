package org.aelion.pubs.pubs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CategoryToCommunityDto {

    private Long id;

    private String communityId;

    private Long categoryId;

    private Long preferenciesFactor;
}

