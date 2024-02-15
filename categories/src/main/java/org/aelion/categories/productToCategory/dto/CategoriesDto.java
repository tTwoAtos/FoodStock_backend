package org.aelion.categories.productToCategory.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CategoriesDto {
    private List<String> categoriesIds = new ArrayList<>();
}
