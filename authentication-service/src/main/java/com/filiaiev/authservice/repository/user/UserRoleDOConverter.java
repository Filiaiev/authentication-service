package com.filiaiev.authservice.repository.user;

import jakarta.persistence.AttributeConverter;

public class UserRoleDOConverter implements AttributeConverter<UserRoleDO, String> {

    @Override
    public String convertToDatabaseColumn(UserRoleDO attribute) {
        return attribute.value;
    }

    @Override
    public UserRoleDO convertToEntityAttribute(String value) {
        return UserRoleDO.forValue(value);
    }
}
