package com.example.dynamodb.rest;


import com.example.dynamodb.entity.Customer;
import com.example.dynamodb.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerRestEndPoint {

  private final CustomerRepository repository;

  @GetMapping("customers")
  public ResponseEntity<List<Customer>> getCustomers() {
    log.info("Getting all the customers");
    List<Customer> customerList = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(customerList);
  }

  @PostMapping("customer")
  public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid final Customer customer) {
    log.info("Saving the new customer = {} into db", customer.toString());
    Customer savedCustomer = repository.save(customer);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
  }


}
