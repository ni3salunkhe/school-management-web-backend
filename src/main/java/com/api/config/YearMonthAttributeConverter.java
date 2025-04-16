package com.api.config;

import java.time.YearMonth;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth, String>{
	
	  @Override
	    public String convertToDatabaseColumn(YearMonth attribute) {
	        return (attribute == null ? null : attribute.toString());
	    }

	    @Override
	    public YearMonth convertToEntityAttribute(String dbData) {
	        return (dbData == null ? null : YearMonth.parse(dbData));
	    }
}
