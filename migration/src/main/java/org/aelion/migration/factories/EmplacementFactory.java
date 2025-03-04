package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CommunityDto;
import org.aelion.migration.dto.EmplacementDto;

import java.util.ArrayList;
import java.util.List;

public class EmplacementFactory {
    public static List<EmplacementDto> generate(List<CommunityDto> communities) {
        Faker faker = new Faker();
        List<EmplacementDto> result = new ArrayList<EmplacementDto>();

        communities.forEach(community -> {
            Integer number = faker.random().nextInt(1, 7);

            for (int i = 0; i < number; i++) {
                EmplacementDto emplacement = new EmplacementDto();
                emplacement.setName(generateRandomStorageLocation());
                emplacement.setCommunityId(community.getId());

                result.add(emplacement);
            }
        });

        return result;
    }

    public static String generateRandomStorageLocation() {
        Faker faker = new Faker();
        String[] storageLocations = {"placard", "frigo", "congélateur", "étagère", "cellier", "cave", "grenier"};
        return faker.options().option(storageLocations);
    }
}
