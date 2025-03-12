package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CommunityDto;
import org.aelion.migration.dto.RoleEntityDto;
import org.aelion.migration.dto.UserEntityDto;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    public static List<UserEntityDto> generate(Integer number, List<CommunityDto> communities) {
        Faker faker = new Faker();
        List<UserEntityDto> result = new ArrayList<UserEntityDto>();
        RoleEntityDto roleUser = new RoleEntityDto();
        roleUser.setId(2L);
        roleUser.setName("User");
        roleUser.setSlug("ROLE_USER");

        for (int i = 0; i < number; i++) {
            UserEntityDto user = new UserEntityDto();
            user.setFirstname(faker.name().fullName().split(" ")[0]);
            user.setLastname(faker.name().fullName().split(" ")[1]);
            user.setPassword("$2a$10$2V/T0mXhpBKwV7NKNGLZd.MB9AIEuDktn5uXlMrgpC0qr4GUO0e5C"); // Correspond to Test23@@
            user.setEmail(faker.internet().emailAddress());
            user.setRole(roleUser);
            user.setGender(faker.random().nextInt(0, 1));
            user.setLoggedInCommunityId(1); // needed for creation but setted later based on the community

            result.add(user);
        }

        return result;
    }
}
