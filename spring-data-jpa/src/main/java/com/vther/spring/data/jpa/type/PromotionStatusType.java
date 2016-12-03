package com.vther.spring.data.jpa.type;

import lombok.Getter;

@Getter
public enum PromotionStatusType {

    DRAFT(1, "DRAFT"),
    TEST(2, "TEST"),
    RELEASE(3, "RELEASED"),
    SUSPEND(4, "SUSPENDED"),
    RETIRE(5, "DRAFT"),
    ABANDON(6, "DRAFT");

    private Integer code;
    private String desc;

    PromotionStatusType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
