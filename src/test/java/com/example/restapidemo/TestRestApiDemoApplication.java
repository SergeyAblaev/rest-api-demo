package com.example.restapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestRestApiDemoApplication {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:14.7-alpine3.17");
    }

    public static void main(String[] args) {
        SpringApplication.from(RestApiDemoApplication::main).with(TestRestApiDemoApplication.class).run(args);
    }

}
