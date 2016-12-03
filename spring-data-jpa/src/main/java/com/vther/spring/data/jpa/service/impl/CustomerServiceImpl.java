package com.vther.spring.data.jpa.service.impl;

import com.vther.spring.data.jpa.dao.CustomerDao1;
import com.vther.spring.data.jpa.entity.self.Customer;
import com.vther.spring.data.jpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {


    private CustomerDao1 customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao1 customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    public void createCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable) {
        return customerDao.findByAgeGreaterThan(age, pageable);
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}