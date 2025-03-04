package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
    public static List<ProductDto> generate(Integer number) {
        Faker faker = new Faker();
        List<ProductDto> result = new ArrayList<ProductDto>();

        for (int i = 0; i < number; i++) {
            ProductDto product = new ProductDto();
            product.setEANCode(String.valueOf(faker.number().randomNumber(13, true)));
            product.setName(faker.commerce().productName());
            product.setNbAdded(Long.valueOf(faker.random().nextInt(0, 100)));
            product.setNbScanned(Long.valueOf(faker.random().nextInt(0, 1000)));
            result.add(product);
        }

        return result;
    }
}
