package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.work.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PromotionDao extends JpaRepository<Promotion, Integer>, JpaSpecificationExecutor<Promotion> {
}
