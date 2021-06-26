package com.example.dynamodb.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableDynamoDBRepositories(basePackages = "com.example.dynamodb.repo")
public class DynamodbConfiguration {

  @Value("${amazon.dynamodb.endpoint}")
  String endpoint;
  @Value("${amazon.aws.access-key}")
  String accessKey;
  @Value("${amazon.aws.secret-key}")
  String secretKey;
  @Value("${amazon.aws.region}")
  String region;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder
      .standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
      .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
      .build();
  }

}
