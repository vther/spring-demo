package com.vther.spring.data.jpa.entity.self;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1212061447845144260L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String orderName;

    private Integer orderType;

    private Double money;

    public Order() {
    }

    public Order(String orderName, Integer orderType, Double money) {
        this.orderName = orderName;
        this.orderType = orderType;
        this.money = money;
    }


}
