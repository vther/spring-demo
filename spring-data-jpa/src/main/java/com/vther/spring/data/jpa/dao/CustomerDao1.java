package com.vther.spring.data.jpa.dao;

import java.util.List;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CustomerDao1 extends Repository<Customer, Long> {

	void save(Customer customer);

	Customer findByCustomerId(Long customerId);

	List<Customer> findAll();

	Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable);
}
