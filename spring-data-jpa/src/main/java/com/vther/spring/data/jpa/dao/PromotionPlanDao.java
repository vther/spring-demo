package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.domain.PromotionPlanInfoList;
import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionPlanDao extends JpaRepository<PromotionPlan, Integer> {

    @Query(value = "SELECT new com.vther.spring.data.jpa.domain.PromotionPlanInfoList( p, n.promotionName)  " +
            "FROM PromotionPlan p,Promotion n WHERE n.promotionId=p.promotionId")
    List<PromotionPlanInfoList> findPromotionPlanInfoList();

}
