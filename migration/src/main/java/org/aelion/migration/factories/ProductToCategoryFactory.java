package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CategoryDto;
import org.aelion.migration.dto.ProductDto;
import org.aelion.migration.dto.ProductToCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class ProductToCategoryFactory {
    public static List<ProductToCategoryDto> generate(Integer number, List<ProductDto> products, List<CategoryDto> categories) {
        Faker faker = new Faker();
        List<ProductToCategoryDto> result = new ArrayList<ProductToCategoryDto>();

        for (int i = 0; i < number; i++) {
            ProductToCategoryDto pToCat = new ProductToCategoryDto();

            if (i < products.size()) {
                pToCat.setProductId(products.get(i).getEANCode());
            } else {
                pToCat.setProductId(products.get(faker.random().nextInt(0, products.size() - 1)).getEANCode());
            }

            pToCat.setCategoryId(categories.get(faker.random().nextInt(0, categories.size() - 1)).getId());

            result.add(pToCat);
        }

        return result;
    }
}
