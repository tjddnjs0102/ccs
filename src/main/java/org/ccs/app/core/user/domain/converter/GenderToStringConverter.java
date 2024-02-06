package org.ccs.app.core.user.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.ccs.app.core.user.domain.Gender;

@Converter
public class GenderToStringConverter implements AttributeConverter<Gender, String> {

    // Gender 열거형 값을 데이터베이스 컬럼에 저장하기 위한 문자열로 변환하는 메서드
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == null) {
            return null;
        }
        switch (attribute) {
            case MALE:
                return "M";
            case FEMALE:
                return "F";
            default:
                throw new IllegalArgumentException("Unknown gender: " + attribute);
        }
    }

    // 데이터베이스 컬럼에서 읽어온 문자열을 Gender 열거형 값으로 변환하는 메서드
    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        switch (dbData) {
            case "M":
                return Gender.MALE;
            case "F":
                return Gender.FEMALE;
            default:
                throw new IllegalArgumentException("Unknown gender from database: " + dbData);
        }
    }
}
