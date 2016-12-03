package com.vther.spring.data.jpa.entity.work;

import com.vther.spring.data.jpa.type.PromotionStatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class PromotionStatusConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer value) {
        for (PromotionStatusType type : PromotionStatusType.values()) {
            if (type.getCode().equals(value)) {
                return type.getDesc();
            }
        }
        return null;
    }

    @Override
    public Integer convertToEntityAttribute(String value) {
        return value == null ? null : PromotionStatusType.valueOf(value).getCode();
    }
}