package org.aelion.myowngateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyownGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyownGatewayApplication.class, args);
    }
}