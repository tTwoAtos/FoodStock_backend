package org.aelion.categories.productToCategory.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductToCategoryResponse {

    private String id;

    private List<String> categoryIds;

    private String productId;
}
