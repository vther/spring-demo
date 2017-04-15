package com.vther.spring.data.jpa.service.impl;

import com.vther.spring.data.jpa.dao.CustomerDao;
import com.vther.spring.data.jpa.entity.firststudy.Customer;
import com.vther.spring.data.jpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {


    private CustomerDao customerDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer checkBeforeSaveCustomer(Customer customer) {
        if (entityManager.find(Customer.class, customer.getCustomerId()) == null) {
            return customerDao.save(customer);
        }
        return null;
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
