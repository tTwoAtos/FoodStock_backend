package org.aelion.categories.categories.dto;

import lombok.Getter;
import lombok.Setter;
import org.aelion.categories.categories.Category;

import java.util.List;
@Getter
@Setter
public class CategoriesDto {
    private List<Category> categories;
}
