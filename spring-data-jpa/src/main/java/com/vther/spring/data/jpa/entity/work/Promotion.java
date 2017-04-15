package com.vther.spring.data.jpa.entity.work;

import com.vther.spring.data.jpa.entity.converter.PromotionStatusConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_PROMOTION")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer promotionId;
    private String promotionName;
    @Convert(converter = PromotionStatusConverter.class)
    private Integer promotionStatus;
    private Date createTime;
    private String creator;
    private Date validTime;
    private Date expireTime;
}
