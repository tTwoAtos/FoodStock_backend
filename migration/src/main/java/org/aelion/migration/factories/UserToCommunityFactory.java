package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CommunityDto;
import org.aelion.migration.dto.UserEntityDto;
import org.aelion.migration.dto.UserToCommunityDto;

import java.util.ArrayList;
import java.util.List;

public class UserToCommunityFactory {
    public static List<UserToCommunityDto> generate(List<UserEntityDto> users, List<CommunityDto> communities) {
        Faker faker = new Faker();
        List<UserToCommunityDto> result = new ArrayList<UserToCommunityDto>();

        for (int i = 0; i < users.size(); i++) {
            UserToCommunityDto uToc = new UserToCommunityDto();
            uToc.setCommunityId(communities.get(faker.random().nextInt(0, communities.size() - 1)).getId());
            uToc.setUser(users.get(i));

            result.add(uToc);
        }

        return result;
    }
}
