package com.vther.spring.data.jpa.specification;

import com.vther.spring.data.jpa.entity.self.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification implements Specification<Customer> {

    private Long customerId;

    private String name;

    private Integer age;

    public CustomerSpecification(Long customerId, String name, Integer age) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
    }

    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<String> customerIdRoot = root.get("customerId");

        Path<String> nameRoot = root.get("name");
        Path<Integer> ageRoot = root.get("age");

        List<Predicate> list = new ArrayList<Predicate>();
        if (customerId != null) {
            list.add(builder.equal(customerIdRoot, customerId));
        }
        if (age != null) {
            list.add(builder.equal(ageRoot, age));
        }
        if (name != null) {
            list.add(builder.like(nameRoot, name));
        }
        Predicate[] array = new Predicate[list.size()];
        return builder.and(list.toArray(array));
    }

}
