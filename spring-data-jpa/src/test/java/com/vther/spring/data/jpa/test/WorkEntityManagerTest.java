package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.PromotionDao;
import com.vther.spring.data.jpa.dao.PromotionPlanDao;
import com.vther.spring.data.jpa.entity.work.Promotion;
import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import com.vther.spring.data.jpa.type.PromotionPlanType;
import com.vther.spring.data.jpa.type.PromotionStatusType;
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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class WorkEntityManagerTest {

    private static Logger LOG = LoggerFactory.getLogger(WorkEntityManagerTest.class);
    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private PromotionPlanDao promotionPlanDao;

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder criteriaBuilder;

    @Before
    public void setUp() throws Exception {
        criteriaBuilder = em.getCriteriaBuilder();
    }

    private void beautyPrint(Object o) {
        LOG.info("---------------------");
        LOG.info("");
        LOG.info("---------------------");
        LOG.info("--------------------{}", o);
        LOG.info("");
        LOG.info("---------------------");
    }

    @Test
    public void testMakeData() {
        Promotion promotion = new Promotion();
        promotion.setCreateTime(new Date());
        promotion.setCreator("admin");
        promotion.setPromotionName("测试活动");
        promotion.setCreateTime(new Date());
        promotion.setValidTime(new Date());
        promotion.setExpireTime(new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000));
        promotion.setPromotionStatus(PromotionStatusType.DRAFT.getCode());
        promotionDao.save(promotion);

        PromotionPlan promotionPlan = new PromotionPlan();
        promotionPlan.setCreateTime(new Date());
        promotionPlan.setCreator("admin");
        promotionPlan.setPlanName("测试计划");
        promotionPlan.setCreateTime(new Date());
        promotionPlan.setPlanStatus(PromotionStatusType.DRAFT.getCode());
        promotionPlan.setPromotionId(promotion.getPromotionId());
        promotionPlan.setPlanType(PromotionPlanType.COUPON.getCode());
        promotionPlanDao.save(promotionPlan);
    }

    @Test
    public void testQueryForAListOfObjects() {
        TypedQuery<Promotion> query = em.createQuery("SELECT p FROM Promotion WHERE p.expireTime > :nowTime", Promotion.class);
        query.setParameter("nowTime", new Date());
        List<Promotion> result = query.getResultList();
        beautyPrint(result);

        CriteriaQuery<Promotion> criteriaQuery = criteriaBuilder.createQuery(Promotion.class);
        Root<Promotion> employee = criteriaQuery.from(Promotion.class);
        criteriaQuery.where(criteriaBuilder.equal(employee.get("promotionId"), 1));
        Promotion result2 = em.createQuery(criteriaQuery).getSingleResult();
        beautyPrint(result2);
    }

    @Test
    public void testQuery() {
        beautyPrint(promotionPlanDao.findPromotionPlanInfoList());
    }

}
