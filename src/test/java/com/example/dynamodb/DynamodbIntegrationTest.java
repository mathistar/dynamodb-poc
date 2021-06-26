package com.example.dynamodb;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.example.dynamodb.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@RequiredArgsConstructor
class DynamodbIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate;

  static {
   initializeDynamoDB();
  }

  private static void initializeDynamoDB() {
    System.setProperty("sqlite4java.library.path", "native-libs"); // to use the sql4lite copied libraries in the folder defined in maven plugin.
    String port = "8000";
    try {
      log.info("Dynamo DB Inmemory DB server going to start");
      DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
      server.start();     //start the server on available port.

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  void customerUpdateTest() {
    Customer customer = Customer.builder()
      .role("Manager")
      .name("Ragu")
      .dateOfBirth(LocalDate.parse("2010-01-12"))
      .build();

    HttpHeaders headers = new HttpHeaders();
    headers.set("content-type", MediaType.APPLICATION_JSON.toString());
    HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

    ResponseEntity<Customer> responseEntity = restTemplate.exchange("/api/customer", HttpMethod.POST, request, Customer.class);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    ResponseEntity<List<Customer>> responseCustomerList = restTemplate.exchange("/api/customers", HttpMethod.GET,
      null, new ParameterizedTypeReference<List<Customer>>() {});

    List<Customer> customerList = responseCustomerList.getBody();

    assertEquals(customerList.size(), 1);
    assertEquals(customerList.get(0).getCustomerId(), responseEntity.getBody().getCustomerId());

  }

}
