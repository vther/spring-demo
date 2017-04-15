package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.CustomerDao;
import com.vther.spring.data.jpa.entity.firststudy.Customer;
import com.vther.spring.data.jpa.service.ICustomerService;
import com.vther.spring.data.jpa.specification.CustomerSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class CustomerDaoTest {
    private static Logger LOG = LoggerFactory.getLogger(CustomerDaoTest.class);
    @Resource
    private ICustomerService customerService;
    @Resource
    private CustomerDao customerDao;

    @Test
    public void testCreateTables() {

    }

    @Test
    public void testQueryAll() {
        List<Customer> customers = customerService.findAll();
        for (Customer cus : customers) {
            LOG.info("testQueryAll, customer = {}", cus);
        }
    }

    @Test
    public void testQueryByProperty() {
        System.out.println(customerService.findByAgeGreaterThan(10, new PageRequest(0, 2)));
    }

    @Test
    public void test22() {
        List<Customer> customers = customerDao.findAll();
        for (Customer cus : customers) {
            System.out.println(cus);
        }
    }

    @Test
    public void test() {
        CustomerSpecification spec = new CustomerSpecification(1L, "a", 2);
        List<Customer> customers = customerDao.findAll(spec);
        for (Customer cus : customers) {
            System.out.println(cus);
        }
    }
}
