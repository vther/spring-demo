package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao3 extends JpaRepository<Customer, Long> {

}
