package ru.toolkas.cc.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

@ActiveProfiles("it")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = {
        "spring.liquibase.change-log=classpath:/db/db.changelog-it.xml"
})
public class CurrencyControllerIT {
  @Container
  public static PostgreSQLContainer sqlContainer = new PostgreSQLContainer("postgres:13.3").withDatabaseName("cc").withUsername("sa").withPassword("sa");

  @LocalServerPort
  private Integer port;

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
    registry.add("spring.datasource.password", sqlContainer::getPassword);
    registry.add("spring.datasource.username", sqlContainer::getUsername);
  }

  @BeforeAll
  static void startAll() {
    sqlContainer.start();
  }

  @Test
  void testConvert() {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> http = new HttpEntity<>("{\"from\": \"USD\",\"to\": \"EUR\",\"amount\": 1}", headers);

    ResponseEntity<BigDecimal> response = restTemplate.exchange("http://localhost:" + port + "/currency/convert", HttpMethod.POST, http, BigDecimal.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("0.9198453771924071164803634656247430", response.getBody().toPlainString());
  }

  @AfterAll
  static void stopAll() {
    sqlContainer.stop();
  }
}
