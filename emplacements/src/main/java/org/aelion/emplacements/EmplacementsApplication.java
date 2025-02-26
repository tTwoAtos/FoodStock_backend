package org.aelion.emplacements;

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
@Info(title = "Emplacement API", version = "${springdoc.version}", description = "Documentation Emplacement API v1.0")
)
public class EmplacementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplacementsApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
