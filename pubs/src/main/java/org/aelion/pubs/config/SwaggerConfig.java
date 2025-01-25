package org.aelion.pubs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(ServletContext servletContext) {
        Server server = new Server().url("http://localhost:5000");
        return new OpenAPI()
                .servers(List.of(server));
    }
}