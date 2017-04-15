package com.vther.spring.data.jpa.entity.memdbtest;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_memdbtest_student")
public class Student implements Serializable {
    private static final long serialVersionUID = -6146935825517747043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String lastName;


    private String firstName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

}