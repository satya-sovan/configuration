package com.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testInventoryServiceDefaultConfig() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/inventory-service/default",
            String.class
        );
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("inventory-service");
    }

    @Test
    void testInventoryServiceDevConfig() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/inventory-service/dev",
            String.class
        );
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("inventory-dev");
    }
} 