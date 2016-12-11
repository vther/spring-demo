package com.vther.spring.data.jpa.entity.work;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_EXTRACHANCERECORD_VIEW")
public class ExtraChanceRecordView {
    @Id
    private Integer id;//推薦人ID
    private String customerId;//推薦人ID
    private Integer promotionId;
    private String customerName;//推薦人姓名
    private Integer totalNum;// 增加的次數
    private String promotionName;
}
