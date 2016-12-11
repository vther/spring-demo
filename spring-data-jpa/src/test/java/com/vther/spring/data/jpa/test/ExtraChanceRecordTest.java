package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.ExtraChanceRecordDao;
import com.vther.spring.data.jpa.dao.ExtraChanceRecordViewDao;
import com.vther.spring.data.jpa.entity.work.ExtraChanceRecord;
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
public class ExtraChanceRecordTest {

    private static Logger LOG = LoggerFactory.getLogger(ExtraChanceRecordTest.class);
    @Autowired
    private ExtraChanceRecordDao extraChanceRecordDao;
    @Autowired
    private ExtraChanceRecordViewDao extraChanceRecordViewDao;

    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = entityManager.getCriteriaBuilder();
    }

    private void beautyPrint(Object o) {
        LOG.info("----------------------");
        LOG.info("");
        LOG.info("----------------------");
        LOG.info("--------------------{}", o);
        LOG.info("");
        LOG.info("----------------------");
    }

    @Test
    public void testMakeData() {
        ExtraChanceRecord record = new ExtraChanceRecord();
        record.setAddNum(1);
        record.setCustomerId("customerId1");
        record.setCustomerName("customerName1");
        record.setPromotionId(1);
        record.setRecommendId("recommendId1");
        record.setRecommendName("recommendName1");
        extraChanceRecordDao.save(record);

        ExtraChanceRecord record2 = new ExtraChanceRecord();
        record2.setAddNum(1);
        record2.setCustomerId("customerId1");
        record2.setCustomerName("customerName1");
        record2.setPromotionId(1);
        record2.setRecommendId("recommendId2");
        record2.setRecommendName("recommendName2");
        extraChanceRecordDao.save(record2);

        ExtraChanceRecord record3 = new ExtraChanceRecord();
        record3.setAddNum(1);
        record3.setCustomerId("customerId2");
        record3.setCustomerName("customerName2");
        record3.setPromotionId(1);
        record3.setRecommendId("recommendId3");
        record3.setRecommendName("recommendName3");
        extraChanceRecordDao.save(record3);
    }

    @Test
    public void testMakeData2() {
        ExtraChanceRecord record = new ExtraChanceRecord();
        record.setAddNum(1);
        record.setCustomerId("customerId1");
        record.setCustomerName("customerName1");
        record.setPromotionId(51);
        record.setRecommendId("recommendId1");
        record.setRecommendName("recommendName1");
        extraChanceRecordDao.save(record);

        ExtraChanceRecord record2 = new ExtraChanceRecord();
        record2.setAddNum(1);
        record2.setCustomerId("customerId1");
        record2.setCustomerName("customerName1");
        record2.setPromotionId(51);
        record2.setRecommendId("recommendId2");
        record2.setRecommendName("recommendName2");
        extraChanceRecordDao.save(record2);

        ExtraChanceRecord record3 = new ExtraChanceRecord();
        record3.setAddNum(1);
        record3.setCustomerId("customerId2");
        record3.setCustomerName("customerName2");
        record3.setPromotionId(51);
        record3.setRecommendId("recommendId3");
        record3.setRecommendName("recommendName3");
        extraChanceRecordDao.save(record3);
    }

    @Test
    public void testQueryView() {
        System.out.println(extraChanceRecordViewDao.findAll());
    }

    @Test
    public void testQuery() {
        beautyPrint(entityManager.createQuery("select r from ExtraChanceRecord r ,Promotion p where p.promotionId = r.promotionId group by r.customerId,r.promotionId").getResultList());
        beautyPrint(entityManager.createQuery("select t.promotionId,c.customerId from Promotion t , (select r.promotionId,r.customerId from ExtraChanceRecord r ,Promotion p where p.promotionId = r.promotionId group by r.customerId,r.promotionId ) c ").getResultList());
    }
}
