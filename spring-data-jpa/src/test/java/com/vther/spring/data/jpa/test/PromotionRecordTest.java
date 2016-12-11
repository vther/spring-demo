package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.PromotionRecordDao;
import com.vther.spring.data.jpa.entity.work.PromotionRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class PromotionRecordTest {

    private static Logger LOG = LoggerFactory.getLogger(PromotionRecordTest.class);
    @Autowired
    private PromotionRecordDao promotionRecordDao;

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = em.getCriteriaBuilder();
    }

    private void beautyPrint(Object o) {
        LOG.info("-----------------------");
        LOG.info("");
        LOG.info("-----------------------");
        LOG.info("--------------------{}", o);
        LOG.info("");
        LOG.info("-----------------------");
    }

    @Test
    public void testMakeData() {
        PromotionRecord record = new PromotionRecord();
        record.setCustomerId("customerId1");
        record.setCustomerName("customerName1");
        record.setPromotionId(1);
        promotionRecordDao.save(record);

        PromotionRecord record2 = new PromotionRecord();
        record2.setCustomerId("customerId1");
        record2.setCustomerName("customerName1");
        record2.setPromotionId(1);
        promotionRecordDao.save(record2);


    }


    @Test
    public void testQueryView() {
        beautyPrint(promotionRecordDao.findAll());
    }
}
