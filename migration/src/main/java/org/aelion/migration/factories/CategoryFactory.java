package org.aelion.migration.factories;

import org.aelion.migration.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryFactory {
    public static List<CategoryDto> generate() {
        List<CategoryDto> result = new ArrayList<CategoryDto>();

        CategoryDto city1 = new CategoryDto();
        city1.setName("Drinks");

        result.add(city1);

        CategoryDto city2 = new CategoryDto();
        city2.setName("Fruits_And_Vegetables");

        result.add(city2);

        CategoryDto city3 = new CategoryDto();
        city3.setName("Starches");

        result.add(city3);

        CategoryDto city4 = new CategoryDto();
        city4.setName("Meat_Fish_Egg");

        result.add(city4);

        CategoryDto city5 = new CategoryDto();
        city5.setName("Dairy_Products");

        result.add(city5);

        CategoryDto city6 = new CategoryDto();
        city6.setName("Sugary_Products");

        result.add(city6);

        CategoryDto city7 = new CategoryDto();
        city7.setName("Condiments");

        result.add(city7);
        return result;
    }
}
