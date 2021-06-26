package com.example.dynamodb.repo;

import com.example.dynamodb.entity.Customer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableScan
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

  @EnableScanCount
  long countByRole(String role);

  List<Customer> findAllByRole(String role);
}

