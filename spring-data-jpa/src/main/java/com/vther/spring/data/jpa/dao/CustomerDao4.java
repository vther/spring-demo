package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao4 extends CrudRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {


}
