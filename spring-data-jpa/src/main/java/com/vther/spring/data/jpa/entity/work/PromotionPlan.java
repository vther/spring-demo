package com.vther.spring.data.jpa.entity.work;

import com.vther.spring.data.jpa.entity.converter.PromotionPlanTypeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_PROMOTIONPLAN")
public class PromotionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer planId;
    private String planName;
    private Integer planStatus;
    @Convert(converter = PromotionPlanTypeConverter.class)
    private Integer planType;
    private Date createTime;
    private String creator;
    private Integer promotionId;
}
