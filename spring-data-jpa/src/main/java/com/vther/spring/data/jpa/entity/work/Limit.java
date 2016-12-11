package com.vther.spring.data.jpa.entity.work;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_LIMIT")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String limitKey;
    private String limitValue;
    private Integer promotionId;

}
