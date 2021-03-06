package com.michno.organizer.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

import static java.sql.Date.valueOf;


@Converter(autoApply = true)
public class DatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            Date date = valueOf(attribute);                                     //null check!!
            return date;
        } else return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData != null)
            return dbData.toLocalDate();
        else return null;
        // return LocalDate date = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());; -- Java9
    }
}
