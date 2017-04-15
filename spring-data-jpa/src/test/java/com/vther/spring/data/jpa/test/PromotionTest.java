package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.PromotionDao;
import com.vther.spring.data.jpa.dao.PromotionPlanDao;
import com.vther.spring.data.jpa.domain.QueryPromotionReq;
import com.vther.spring.data.jpa.entity.work.Promotion;
import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import com.vther.spring.data.jpa.specification.PromotionSpecification;
import com.vther.spring.data.jpa.type.PromotionPlanType;
import com.vther.spring.data.jpa.type.PromotionStatusType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class PromotionTest {

    private static Logger LOG = LoggerFactory.getLogger(PromotionTest.class);
    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private PromotionPlanDao promotionPlanDao;


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
    public void testQueryPromotionPlanInfoList() {
        // 使用SpringDataJPA关联两个表进行查询
        beautyPrint(promotionPlanDao.findPromotionPlanInfoList());
    }

    @Test
    public void testQueryForMultiParams() {
        // 按照多个字段查询
        QueryPromotionReq req = new QueryPromotionReq();
        req.setPromotionId(1);
        req.setCreateTimeStart("2016-12-03T00:55:29Z");
        req.setCreateTimeEnd("2016-12-05T00:55:29Z");
        req.setCreator("admin");
        req.setStartTime("2016-12-10T00:55:29Z");
        req.setEndTime("2016-12-14T00:55:29Z");
        req.setPromotionStatus(PromotionStatusType.DRAFT.getCode());
        req.setPromotionName("试活");
        Specification<Promotion> specification = new PromotionSpecification(req);
        Long count = promotionDao.count(specification);
        beautyPrint(count);
        List<Promotion> result = promotionDao.findAll(specification);//必须实现JpaSpecificationExecutor
        beautyPrint(result);
    }

    @Test
    public void testQueryForPagesAndOrderBy() {
        // 按照多个字段orderBy
        Sort sort = new Sort(Sort.Direction.DESC, "createTime").and(new Sort(Sort.Direction.ASC, "promotionId"));
        Page<Promotion> page = promotionDao.findAll(new PageRequest(0, 10, sort));
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果list：" + page.getContent());
        System.out.println("当前页码：" + page.getNumber());
        System.out.println("每页数量：" + page.getSize());
    }
}
