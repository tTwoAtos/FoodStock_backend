package org.aelion.myowngateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfig {
    @Bean
    public List<GroupedOpenApi> groupedOpenApis(
            RouteDefinitionLocator locator, SwaggerUiConfigProperties swaggerUiConfigProperties) {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrls = swaggerUiConfigProperties.getUrls();

        if (swaggerUrls == null)
            swaggerUrls = new HashSet<>();

        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> finalSwaggerUrls = swaggerUrls;
        var res = locator.getRouteDefinitions()
                .toStream()
                .map(RouteDefinition::getId)
                .filter(id -> id.endsWith("-SERVICE"))
                .map(
                        routeDefinitionId -> {
                            String name = extractNameFromRouteDefinitionId(routeDefinitionId).toLowerCase();

                            finalSwaggerUrls.add(
                                    new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                                            name,
                                            "/" + name + "/v3/api-docs",
                                            routeDefinitionId));
                            System.out.println("/" + name + "/**" + " --- " + "/" + name + "/v3/api-docs");
                            return GroupedOpenApi.builder()
                                    .pathsToMatch("/" + name + "/**")
                                    .group(name)
                                    .build();
                        })
                .toList();

        swaggerUiConfigProperties.setUrls(finalSwaggerUrls);

        return res;
    }

    private String extractNameFromRouteDefinitionId(String routeDefinitionId) {
        return routeDefinitionId.replace("-SERVICE", "");
    }
}
