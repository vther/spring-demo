package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.firststudy.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository
 * CrudRepository，增加创建增删改查方法，
 * PagingAndSortingRepository 增加分页
 * JpaRepository 增加批量操作方法
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Customer findByCustomerId(Long customerId);

    Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable);
}
