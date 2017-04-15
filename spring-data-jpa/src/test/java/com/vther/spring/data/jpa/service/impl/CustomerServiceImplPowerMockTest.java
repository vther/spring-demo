package com.vther.spring.data.jpa.service.impl;


import com.vther.spring.data.jpa.entity.memdbtest.Student;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest(CustomerServiceImplPowerMockTest.class)
public class CustomerServiceImplPowerMockTest {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    @BeforeClass
    public static void initTestFixture() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TEST_Persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void closeTestFixture() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void initTest() throws Exception {

    }

    @Test
    public void createCustomer() throws Exception {
        System.out.println(entityManager.find(Student.class, 1L));
    }

    @Test
    public void findByAgeGreaterThan() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}