package com.vther.spring.data.jpa.service.impl;

import com.vther.spring.data.jpa.config.SpringConfiguration;
import com.vther.spring.data.jpa.dao.CustomerDao;
import com.vther.spring.data.jpa.entity.firststudy.Customer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Duration;
import java.time.Instant;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest(CustomerServiceImplTest.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerServiceImplTest {

    private static Instant start;
    private CustomerServiceImpl customerService;
    @Autowired
    private CustomerDao customerDao;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeClass
    public static void before() throws Exception {
        start = Instant.now();
    }

    @AfterClass
    public static void after() throws Exception {
        System.out.println("================== CustomerServiceImplTest end, time costs " +
                Duration.between(start, Instant.now()).getSeconds() + "s ================== ");
    }

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerServiceImpl();
        ReflectionTestUtils.setField(customerService, "customerDao", customerDao);
        ReflectionTestUtils.setField(customerService, "entityManager", entityManager);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkBeforeSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAge(28);
        customer.setName("LiuDanHuang");
        customer.setOrders(null);
        customer.setCustomerId(100L);

        System.out.println("================== checkBeforeSaveCustomer ==================> "
                + customerService.checkBeforeSaveCustomer(customer));
    }


    @Test
    public void createCustomer() throws Exception {

    }

    @Test
    public void findByAgeGreaterThan() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}