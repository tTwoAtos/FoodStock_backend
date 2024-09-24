package org.aelion.Products.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.MediaType;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Définit le JSON comme type de contenu par défaut, necessaire pour faire fonctionner le générateur openapi du frontend
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
