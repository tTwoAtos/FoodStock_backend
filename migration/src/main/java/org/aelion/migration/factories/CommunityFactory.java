package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CityDto;
import org.aelion.migration.dto.CommunityDto;

import java.util.ArrayList;
import java.util.List;

public class CommunityFactory {
    public static List<CommunityDto> generate(Integer number, List<CityDto> cities) {
        Faker faker = new Faker();
        List<CommunityDto> result = new ArrayList<CommunityDto>();

        for (int i = 0; i < number; i++) {
            CommunityDto community = new CommunityDto();
            community.setName("Communauté de " + faker.name().fullName().split(" ")[0]);
            community.setCityCode(cities.get(faker.random().nextInt(0, cities.size() - 1)).getInseeCode());

            result.add(community);
        }

        return result;
    }
}
