package org.aelion.migration.factories;

import com.github.javafaker.Faker;
import org.aelion.migration.dto.CommunityDto;
import org.aelion.migration.dto.EmplacementDto;
import org.aelion.migration.dto.ProductDto;
import org.aelion.migration.dto.ProductToCommunityDto;

import java.util.ArrayList;
import java.util.List;

public class ProductToCommunityFactory {
    public static List<ProductToCommunityDto> generate(Integer number, List<ProductDto> products, List<CommunityDto> communities, List<EmplacementDto> emplacements) {
        Faker faker = new Faker();
        List<ProductToCommunityDto> result = new ArrayList<ProductToCommunityDto>();

        for (int i = 0; i < number; i++) {
            ProductToCommunityDto pToc = new ProductToCommunityDto();
            
            pToc.setCommunityId(communities.get(faker.random().nextInt(0, communities.size() - 1)).getId());
            pToc.setProductId(products.get(faker.random().nextInt(0, products.size() - 1)).getEANCode());
            pToc.setEmplacementId(emplacements.get(faker.random().nextInt(0, emplacements.size() - 1)).getId());
            pToc.setQte(Long.valueOf(faker.random().nextInt(1, 5)));

            result.add(pToc);
        }

        return result;
    }
}
