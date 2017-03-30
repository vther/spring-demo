package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao1 extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long customerId);

    Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable);
}
