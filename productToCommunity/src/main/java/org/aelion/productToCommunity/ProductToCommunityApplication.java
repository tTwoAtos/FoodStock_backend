package org.aelion.productToCommunity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.aelion.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Import(GlobalExceptionHandler.class)
@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Product to Community API", version = "${springdoc.version}", description = "Documentation Product to Community API v1.0")
)
public class ProductToCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductToCommunityApplication.class, args);
    }
}
