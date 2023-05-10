package com.filiaiev.authservice.repository.user;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderDO {

    MALE("Male"),
    FEMALE("Female");

    @JsonValue
    private final String id;
}
