package org.aelion.migration.commands;

import org.aelion.migration.dto.*;
import org.aelion.migration.factories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ShellComponent
public class GenerateFakeData {
    @Autowired
    private UserRepositoryDto userRepository;
    @Autowired
    private CityRepositoryDto cityRepository;
    @Autowired
    private CommunityRepositoryDto communityRepository;
    @Autowired
    private CategoryRepositoryDto categoryRepository;
    @Autowired
    private UserToCommunityRepositoryDto uTocRepository;
    @Autowired
    private CategoryToCommunityRepositoryDto cTocRepository;
    @Autowired
    private ProductRepositoryDto productRepository;
    @Autowired
    private ProductToCommunityRepositoryDto pTocRepository;
    @Autowired
    private EmplacementRepositoryDto emplacementRepository;

    @ShellMethod(key = "generate-data", value = "Génère des données fictives et les enregistre en base de données.")
    @Transactional
    public void generateData() {
        // Cities
        List<CityDto> cities = CityFactory.generate();
        cityRepository.saveAll(cities);
        System.out.println("Generated " + cities.size() + " cities");

        // Categories
        List<CategoryDto> categories = CategoryFactory.generate();
        categoryRepository.saveAll(categories);
        System.out.println("Generated " + categories.size() + " categories");

        // Communities
        List<CommunityDto> communities = CommunityFactory.generate(50, cities);
        communityRepository.saveAll(communities);
        System.out.println("Generated " + communities.size() + " community");

        // Users
        List<UserEntityDto> users = UserFactory.generate(400, communities);
        System.out.println("Generated " + users.size() + " users");

        // User to community
        List<UserToCommunityDto> uToCs = UserToCommunityFactory.generate(users, communities);
        uTocRepository.saveAll(uToCs);
        System.out.println("Generated " + uToCs.size() + " users to community");

        // update user's loggedInCommunityId
        userRepository.saveAll(users);
        users = userRepository.findAll();
        List<UserEntityDto> finalUsers = users;
        uToCs.forEach(uToC -> {
            final UserEntityDto user = finalUsers.stream()
                    .filter(user1 -> user1.getId().equals(uToC.getUser().getId()))
                    .findFirst().get();

            user.setLoggedInCommunityId(uToC.getCommunityId());
        });
        userRepository.saveAll(users);

        // Products
        List<ProductDto> products = ProductFactory.generate(1000);
        productRepository.saveAll(products);
        System.out.println("Generated " + products.size() + " products");

        // Emplacements
        List<EmplacementDto> emplacements = EmplacementFactory.generate(communities);
        emplacementRepository.saveAll(emplacements);
        System.out.println("Generated " + emplacements.size() + " emplacements");

        // Emplacements
        List<ProductToCommunityDto> pToC = ProductToCommunityFactory.generate(2000, products, communities, emplacements);
        pTocRepository.saveAll(pToC);
        System.out.println("Generated " + pToC.size() + " product to community");

        // Category to community
        List<CategoryToCommunityDto> cToCs = CategoryToCommunityFactory.generate(150, categories, communities);
        cTocRepository.saveAll(cToCs);
        System.out.println("Generated " + cToCs.size() + " category to community");
    }
}
