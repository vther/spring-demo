package com.vther.spring.data.jpa.entity.wiki;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class BooleanTFConverter implements AttributeConverter<Boolean, String> {
    public BooleanTFConverter() {
    }

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "T".equals(value);
    }
}