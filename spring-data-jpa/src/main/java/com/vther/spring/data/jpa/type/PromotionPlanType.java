package com.vther.spring.data.jpa.type;

import lombok.Getter;

@Getter
public enum PromotionPlanType {

    COUPON(100, "COUPON"),
    PRODUCT(200, "PRODUCT"),
    EVENT(300, "EVENT"),
    LOTTERY(400, "LOTTERY");

    private Integer code;
    private String desc;

    PromotionPlanType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
