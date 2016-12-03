package com.vther.spring.data.jpa.domain;

import com.vther.spring.data.jpa.entity.work.PromotionPlan;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@ToString
public class PromotionPlanInfo {
    private Integer planId;
    private String planName;
    private Integer planStatus;
    private Integer planType;
    private Date createTime;
    private String creator;
    private Integer promotionId;
    // 多出来这个字段

    private String promotionName;

    public PromotionPlanInfo() {
    }

    public PromotionPlanInfo(PromotionPlan promotionPlan, String promotionName) {
        BeanUtils.copyProperties(promotionPlan, this);
        this.promotionName = promotionName;
    }
}
