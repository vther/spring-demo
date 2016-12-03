package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.domain.PromotionPlanInfo;
import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionPlanDao extends JpaRepository<PromotionPlan, Integer> {

    //这里的Select 必须和PromotionPlanInfo的构造函数对应上
    @Query(value = "SELECT new com.vther.spring.data.jpa.domain.PromotionPlanInfo( p, n.promotionName)  " +
            "FROM PromotionPlan p,Promotion n WHERE n.promotionId=p.promotionId")
    List<PromotionPlanInfo> findPromotionPlanInfoList();

}
