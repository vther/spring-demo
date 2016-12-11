package com.vther.spring.data.jpa.entity.work;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "T_PROMOTIONRECORD")
public class PromotionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerId;//推薦人ID
    private String customerName;//推薦人姓名
    private Integer promotionId;
}
