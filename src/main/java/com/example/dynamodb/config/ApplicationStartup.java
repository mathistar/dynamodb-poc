package com.example.dynamodb.config;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.dynamodb.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("local")
public class ApplicationStartup {
  private final DynamoDBMapper dynamoDBMapper;
  private final AmazonDynamoDB dynamoDB;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    CreateTableRequest customerTableRequest = dynamoDBMapper.generateCreateTableRequest(Customer.class)
      .withProvisionedThroughput(new ProvisionedThroughput(1l,1l));

    log.info("Create customer table if not exists");
    TableUtils.createTableIfNotExists(dynamoDB, customerTableRequest);
  }
}
