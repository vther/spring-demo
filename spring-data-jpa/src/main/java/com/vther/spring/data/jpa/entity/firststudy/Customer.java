package com.vther.spring.data.jpa.entity.firststudy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "t_customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1212061447845144260L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String name;

    private Integer age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private List<Order> orders;

}
