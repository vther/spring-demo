package com.vther.spring.data.jpa.specification;

import com.vther.spring.data.jpa.domain.QueryPromotionReq;
import com.vther.spring.data.jpa.entity.work.Promotion;
import com.vther.spring.data.jpa.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionSpecification implements Specification<Promotion> {

    private QueryPromotionReq req;

    public PromotionSpecification(QueryPromotionReq req) {
        this.req = req;
    }

    public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

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
        Predicate[] array = new Predicate[list.size()];
        return builder.and(list.toArray(array));
    }

}
