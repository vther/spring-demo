package com.vther.spring.data.jpa.entity.work;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_EXTRACHANCERECORD")
public class ExtraChanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerId;//推薦人ID
    private String customerName;//推薦人姓名
    private Integer addNum;// 增加的次數
    private String recommendId;//被推薦人Id
    private String recommendName;//被推薦人姓名
    private Integer promotionId;
}
