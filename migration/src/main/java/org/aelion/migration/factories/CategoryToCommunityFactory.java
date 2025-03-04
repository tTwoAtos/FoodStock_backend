package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CategoryDto;
import org.aelion.migration.dto.CategoryToCommunityDto;
import org.aelion.migration.dto.CommunityDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryToCommunityFactory {
    public static List<CategoryToCommunityDto> generate(Integer number, List<CategoryDto> categories, List<CommunityDto> communities) {
        Faker faker = new Faker();
        List<CategoryToCommunityDto> result = new ArrayList<CategoryToCommunityDto>();

        for (int i = 0; i < number; i++) {
            CategoryToCommunityDto cToc = new CategoryToCommunityDto();
            cToc.setCommunityId(communities.get(faker.random().nextInt(0, communities.size() - 1)).getId());
            cToc.setCategoryId(categories.get(faker.random().nextInt(0, categories.size() - 1)).getId());
            cToc.setPreferenciesFactor(Long.valueOf(faker.random().nextInt(0, 100)));

            result.add(cToc);
        }

        return result;
    }
}
