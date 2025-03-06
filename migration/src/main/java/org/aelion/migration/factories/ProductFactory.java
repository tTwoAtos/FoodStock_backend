package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductFactory {
    public static List<ProductDto> generate(Integer number) {
        Faker faker = new Faker();
        List<ProductDto> result = new ArrayList<ProductDto>();

        for (int i = 0; i < number; i++) {
            ProductDto product = new ProductDto();
            product.setEANCode(String.valueOf(faker.number().randomNumber(13, true)));
            product.setName(generateRandomFood());
            product.setNbAdded(Long.valueOf(faker.random().nextInt(0, 100)));
            product.setNbScanned(Long.valueOf(faker.random().nextInt(0, 1000)));
            result.add(product);
        }

        return result;
    }

    public static String generateRandomFood() {
        // Choisir une méthode aléatoire parmi les options disponibles
        Faker faker = new Faker();
        Random random = new Random();
        int choice = random.nextInt(6); // 6 options disponibles

        switch (choice) {
            case 0:
                return faker.food().dish(); // Plat
            case 1:
                return faker.food().fruit(); // Fruit
            case 2:
                return faker.food().vegetable(); // Légume
            case 3:
                return faker.food().ingredient(); // Ingrédient
            case 4:
                return faker.food().spice(); // Épice
            case 5:
                return faker.food().sushi(); // Sushi
            default:
                return faker.food().dish(); // Par défaut, retourner un plat
        }
    }
}
