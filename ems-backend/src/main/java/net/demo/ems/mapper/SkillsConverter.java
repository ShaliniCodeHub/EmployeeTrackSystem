package net.demo.ems.mapper;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;


public class SkillsConverter implements AttributeConverter<List<String>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return null;
        }
        return String.join(DELIMITER, skills);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Arrays.asList(dbData.split(DELIMITER));
    }
}
