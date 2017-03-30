package com.vther.spring.data.jpa.service;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    Customer checkBeforeSaveCustomer(Customer customer);

    void createCustomer(Customer customer);

    List<Customer> findAll();

    Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable);
}
