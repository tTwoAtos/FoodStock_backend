package org.aelion.categories.categories;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(String code);

    void add(List<String> categories, String productCode);
}
