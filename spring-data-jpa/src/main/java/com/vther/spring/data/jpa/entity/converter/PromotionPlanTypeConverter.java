package com.vther.spring.data.jpa.entity.converter;

import com.vther.spring.data.jpa.type.PromotionPlanType;
import com.vther.spring.data.jpa.type.PromotionStatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class PromotionPlanTypeConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer value) {
        for (PromotionPlanType type : PromotionPlanType.values()) {
            if (type.getCode().equals(value)) {
                return type.getDesc();
            }
        }
        return null;
    }

    @Override
    public Integer convertToEntityAttribute(String value) {
        return value == null ? null : PromotionPlanType.valueOf(value).getCode();
    }
}