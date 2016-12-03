package com.vther.spring.data.jpa.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryPromotionReq {
    private Integer promotionId;
    private String promotionName;
    private Integer promotionStatus;
    private String createTimeStart;
    private String createTimeEnd;
    private String creator;
    private String startTime;
    private String endTime;
}
