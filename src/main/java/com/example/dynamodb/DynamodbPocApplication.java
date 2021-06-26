package com.example.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.example.dynamodb")
@ConfigurationPropertiesScan
public class DynamodbPocApplication {

  public static void main(String[] args) {
    SpringApplication.run(DynamodbPocApplication.class, args);
  }


}
