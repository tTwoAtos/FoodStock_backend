package org.aelion.communities;

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
@Info(title = "Community API", version = "${springdoc.version}", description = "Documentation Community API v1.0")
)
public class CommunitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunitiesApplication.class, args);
    }
}
