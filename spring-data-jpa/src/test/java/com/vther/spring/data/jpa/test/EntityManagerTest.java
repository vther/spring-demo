package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.entity.wiki.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class EntityManagerTest {

    private static Logger LOG = LoggerFactory.getLogger(EntityManagerTest.class);
    @PersistenceContext
    private EntityManager em;


    private CriteriaBuilder criteriaBuilder;

    @Before
    public void setUp() throws Exception {
        criteriaBuilder = em.getCriteriaBuilder();
    }

    private void beautyPrint(Object o) {
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------{}", o);
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
        LOG.info("--------------------");
    }

    @Test
    public void testQueryForAListOfObjects() {
        Query query = em.createQuery("Select e FROM Employee e WHERE e.salary >= 100000");
        List result = query.getResultList();
        beautyPrint(result);

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(employee.get("salary"), 100000));
        List<Employee> result2 = em.createQuery(criteriaQuery).getResultList();
        beautyPrint(result2);
    }

    @Test
    public void testQueryForASingleObject() {
        Query query = em.createQuery("Select e FROM Employee e WHERE e.id = :id");
        query.setParameter("id", 1);
        Employee result2 = (Employee) query.getSingleResult();
        beautyPrint(result2);
    }

    @Test
    public void testQueryForASingleDataElement() {
        Query query = em.createQuery("Select MAX(e.salary) FROM Employee e");
        BigDecimal result3 = (BigDecimal) query.getSingleResult();
        beautyPrint(result3);
    }

    @Test
    public void testQueryForAListOfDataElements() {
        TypedQuery<String> query = em.createQuery("Select e.firstName FROM Employee e", String.class);
        List<String> result4 = query.getResultList();
        beautyPrint(result4);
    }

    @Test
    public void testQueryForAListOfElementArrays() {
        TypedQuery<Object[]> query = em.createQuery("Select e.firstName, e.lastName FROM Employee e", Object[].class);
        List<Object[]> result5 = query.getResultList();
        for (Object[] objects : result5) {
            beautyPrint(objects);
        }
    }

    @Test
    public void testQueryForJoin() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e JOIN e.address a WHERE a.city = :city", Employee.class);
        query.setParameter("city", "深圳");
        List<Employee> result5 = query.getResultList();
        for (Employee objects : result5) {
            beautyPrint(objects);
        }
    }


}
