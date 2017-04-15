package com.vther.spring.data.jpa.service.impl;


import com.vther.spring.data.jpa.config.SpringConfiguration;
import com.vther.spring.data.jpa.entity.memdbtest.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
//@PrepareForTest(CustomerServiceImplPowerMockTest2.class)
public class CustomerServiceImplPowerMockUseSpringTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createCustomer() throws Exception {
        // !!!
        // persistence.xml里面预制了脚本
        System.out.println(entityManager.find(Student.class, 1L));
    }

    @Test
    public void findByAgeGreaterThan() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}