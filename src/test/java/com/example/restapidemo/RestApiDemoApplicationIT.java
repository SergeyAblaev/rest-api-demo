package com.example.restapidemo;

import com.example.restapidemo.unit.dao.entity.StoreDataEntity;
import com.example.restapidemo.unit.dao.repository.StoreDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
class RestApiDemoApplicationIT {
    @Container
    private static final JdbcDatabaseContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:14.7-alpine3.17");
    public static final int LIQUIBASE_CHANGESET_SIZE = 5;

    @DynamicPropertySource
    private static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);

        registry.add("spring.liquibase.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.liquibase.user", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.liquibase.password", POSTGRESQL_CONTAINER::getPassword);
    }

    @Autowired
    StoreDataRepository storeDataRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void getStoreDataRecords (){
        List<StoreDataEntity> entityList = storeDataRepository.findAll();
        Assertions.assertFalse(entityList.isEmpty());
        Assertions.assertEquals(LIQUIBASE_CHANGESET_SIZE, entityList.size());
    }

}
