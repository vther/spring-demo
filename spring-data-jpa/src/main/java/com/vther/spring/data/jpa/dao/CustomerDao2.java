package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao2 extends CrudRepository<Customer, Long> {

}
