package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.work.PromotionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRecordDao extends JpaRepository<PromotionRecord, Integer> {


}
