package com.api.config;

<<<<<<< HEAD
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
=======
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.YearMonth;
import java.sql.Date;

@Converter(autoApply = true)
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth, String> {

    @Override
    public String convertToDatabaseColumn(YearMonth attribute) {
        return (attribute == null ? null : attribute.toString());
    }

    @Override
    public YearMonth convertToEntityAttribute(String dbData) {
        return (dbData == null ? null : YearMonth.parse(dbData));
    }
>>>>>>> 6237fb77c5d87fbfea1df0447b31e3c5c9d7d20b
}
