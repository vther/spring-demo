package com.vther.spring.data.jpa.test;

import com.vther.spring.data.jpa.dao.PromotionDao;
import com.vther.spring.data.jpa.dao.PromotionPlanDao;
import com.vther.spring.data.jpa.domain.PromotionPlanInfo;
import com.vther.spring.data.jpa.domain.QueryPromotionReq;
import com.vther.spring.data.jpa.entity.work.Promotion;
import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import com.vther.spring.data.jpa.specification.PromotionSpecification;
import com.vther.spring.data.jpa.type.PromotionPlanType;
import com.vther.spring.data.jpa.type.PromotionStatusType;
import com.vther.spring.data.jpa.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = em.getCriteriaBuilder();
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
    public void testQueryPromotionPlanInfoList() {
        beautyPrint(promotionPlanDao.findPromotionPlanInfoList());

        CriteriaQuery<PromotionPlanInfo> query = builder.createQuery(PromotionPlanInfo.class);
        Root<PromotionPlan> planRoot = query.from(PromotionPlan.class);
        Root<Promotion> root = query.from(Promotion.class);
        Path<String> promotionName = root.get("promotionName");
        query.multiselect(planRoot, promotionName);//这里的Select 必须和PromotionPlanInfo的构造函数对应上
        query.where(builder.equal(planRoot.get("promotionId").as(Integer.class), root.get("promotionId").as(Integer.class)));
        List<PromotionPlanInfo> result2 = em.createQuery(query).setFirstResult(0).setMaxResults(10).getResultList();
        beautyPrint(result2);

    }

    @Test
    public void testQueryForAListOfObjects() {
        CriteriaQuery<Promotion> criteriaQuery = builder.createQuery(Promotion.class);
        Root<Promotion> employee = criteriaQuery.from(Promotion.class);
        criteriaQuery.where(builder.equal(employee.get("promotionId"), 1));
        Promotion result2 = em.createQuery(criteriaQuery).getSingleResult();
        beautyPrint(result2);
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

        CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
        Root<Promotion> root = query.from(Promotion.class);
        List<Predicate> list = new ArrayList<>();
        if (StringUtils.isNotBlank(req.getPromotionName())) {
            list.add(builder.like(root.get("promotionName").as(String.class), "%" + req.getPromotionName() + "%"));
        }
        if (StringUtils.isNotBlank(req.getCreator())) {
            list.add(builder.equal(root.get("creator").as(String.class), req.getCreator()));
        }
        if (req.getPromotionId() != null) {
            list.add(builder.equal(root.get("promotionId").as(Integer.class), req.getPromotionId()));
        }
        if (req.getPromotionStatus() != null && req.getPromotionStatus() != -1) {//-1表示不限
            list.add(builder.equal(root.get("promotionStatus").as(Integer.class), req.getPromotionStatus()));
        }
        // createTimeStart <= createTime <=  createTimeEnd
        if (StringUtils.isNotBlank(req.getCreateTimeStart())) {
            list.add(builder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), DateUtils.stringToDate(req.getCreateTimeStart())));
        }
        if (StringUtils.isNotBlank(req.getCreateTimeEnd())) {
            list.add(builder.lessThanOrEqualTo(root.get("createTime").as(Date.class), DateUtils.stringToDate(req.getCreateTimeEnd())));
        }
        // startTime <= expireTime AND endTime >= validTime 两段时间有交集：http://blog.csdn.net/misterliwei/article/details/5271307
        if (StringUtils.isNotBlank(req.getStartTime())) {
            list.add(builder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class), DateUtils.stringToDate(req.getStartTime())));
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            list.add(builder.lessThanOrEqualTo(root.get("validTime").as(Date.class), DateUtils.stringToDate(req.getEndTime())));
        }
        Predicate[] predicates = new Predicate[list.size()];
        list.toArray(predicates);
        query.where(predicates);
        System.out.println("----------777777777---------");
        query.select(root);
        List<Promotion> result2 = em.createQuery(query).setFirstResult(0).setMaxResults(10).getResultList();

        beautyPrint(result2);
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

        CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
        Root<Promotion> root = query.from(Promotion.class);
        query.select(root);
        query.orderBy(builder.desc(root.get("createTime").as(Date.class)), builder.asc(root.get("promotionId").as(Integer.class)));
        List<Promotion> result2 = em.createQuery(query).setFirstResult(0).setMaxResults(10).getResultList();
        beautyPrint(result2);
    }

    @Test
    public void testQueryCount() {
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Promotion> root = query.from(Promotion.class);
        query.where(builder.greaterThanOrEqualTo(root.get("promotionId"), 1));
        query.select(builder.countDistinct(root));
        Long count = em.createQuery(query).getSingleResult();
        beautyPrint(count);
    }

    @Test
    public void testOtherQuery() {
        CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
        Root<Promotion> root = query.from(Promotion.class);
        // 更多写法: https://en.wikibooks.org/wiki/Java_Persistence/Criteria
        // -- 1  in -> WHERE (PROMOTIONSTATUS IN (?, ?))
        /* query.where(root.get("promotionStatus").as(Integer.class).
                 in(PromotionStatusType.DRAFT.getCode(), PromotionStatusType.TEST.getCode()));
         query.where(builder.in(root.get("promotionStatus").as(Integer.class)).
                 value(PromotionStatusType.DRAFT.getCode()).value(PromotionStatusType.TEST.getCode()));*/

        // -- 2  between -> WHERE (CREATETIME BETWEEN ? AND ?)
        // query.where(builder.between(root.get("createTime").as(Date.class), DateUtils.stringToDate("2016-12-03T00:55:29Z"), DateUtils.stringToDate("2016-12-13T00:55:29Z")));

        // -- 3  or -> WHERE ((PROMOTIONNAME = ?) OR (PROMOTIONNAME = ?))
        // query.where(builder.or(builder.equal(root.get("promotionName").as(String.class), "Bob"), builder.equal(root.get("promotionName").as(String.class), "Bobby")));

        // -- 4  not -> WHERE NOT (((PROMOTIONNAME = ?) OR (PROMOTIONNAME = ?)))
        /*query.where(builder.not(builder.or(builder.equal(root.get("promotionName").as(String.class), "Bob"),
                builder.equal(root.get("promotionName").as(String.class), "Bobby"))));
        query.where(builder.or(builder.equal(root.get("promotionName").as(String.class), "Bob"),
                builder.equal(root.get("promotionName").as(String.class), "Bobby")).not());*/
        // -- 5  conjunction 配and，disjunction配or，会吧多个条件组合成为一个
         /* Predicate where = builder.conjunction();//整合的时候,必须配and，配or不起作用，相当于1=1，永远为真；如果整合了零个条件，也是相当于1=1，永远为真
        where = builder.and(where, builder.equal(root.get("promotionName").as(String.class), "name1"), builder.equal(root.get("promotionName").as(String.class), "name2"));
        query.where(where);
        Predicate where2 = builder.disjunction();//整合的时候,必须配or，配and相当于1=0，为假；如果整合了零个条件，也是相当于1=0为假
        where2 = builder.and(where2, builder.equal(root.get("promotionName").as(String.class), "name3"), builder.equal(root.get("promotionName").as(String.class), "name4"));
        query.where(where2);*/
        // -- 6 build.diff sum prod quot abs concat ...etc... https://en.wikibooks.org/wiki/Java_Persistence/Criteria
        List<Promotion> result2 = em.createQuery(query).getResultList();
        beautyPrint(result2);
    }

    @Test
    public void testSubQuery() {
        // SELECT t0.* FROM T_PROMOTION t0 WHERE t0.PROMOTIONID IN (SELECT t1.PROMOTIONID FROM T_PROMOTION t1 WHERE (t1.PROMOTIONSTATUS < ?))
        CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
        Root<Promotion> root = query.from(Promotion.class);
        Subquery<Promotion> subQuery = query.subquery(Promotion.class);

        Root<Promotion> subRoot = subQuery.from(Promotion.class);
        subQuery.where(builder.lessThan(subRoot.get("promotionStatus").as(Integer.class), PromotionStatusType.RELEASE.getCode()));
        subQuery.select(subRoot.get("promotionId"));
        query.where(builder.in(root.get("promotionId")).value(subQuery));

        List<Promotion> result2 = em.createQuery(query).getResultList();
        beautyPrint(result2);
    }

    @Test
    public void testSubQuery2() {
        // SELECT t0.* FROM T_PROMOTIONPLAN t0 WHERE t0.PROMOTIONID IN (SELECT t1.PROMOTIONID FROM T_PROMOTION t1)
        CriteriaQuery<PromotionPlan> query = builder.createQuery(PromotionPlan.class);
        Root<PromotionPlan> root = query.from(PromotionPlan.class);
        Subquery<Promotion> subQuery = query.subquery(Promotion.class);

        Root<Promotion> subRoot = subQuery.from(Promotion.class);
        subQuery.select(subRoot.get("promotionId"));
        query.where(builder.in(root.get("promotionId")).value(subQuery));

        List<PromotionPlan> result2 = em.createQuery(query).getResultList();
        beautyPrint(result2);
    }

    @Test
    public void testTupleQuery() {
        CriteriaQuery<Tuple> query = builder.createTupleQuery();
        Root<Promotion> root = query.from(Promotion.class);
        query.multiselect(root.get("promotionId").alias("id"), root.get("promotionName").alias("name"));
        List<Tuple> results = em.createQuery(query).getResultList();
        Integer id = (Integer) results.get(0).get("id");
        String last = (String) results.get(0).get("name");
        beautyPrint(id);
        beautyPrint(last);
    }
}
