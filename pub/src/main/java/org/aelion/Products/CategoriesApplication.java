package org.aelion.Products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CategoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoriesApplication.class, args);
    }
}
