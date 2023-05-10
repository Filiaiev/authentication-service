package com.filiaiev.authservice.resource.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum GenderRO {

    MALE("Male"),
    FEMALE("Female");

    @JsonValue
    public final String value;

    private static final Map<String, GenderRO> gendersMap = Stream.of(GenderRO.values())
            .collect(Collectors.toMap(role -> role.value, Function.identity()));

    @JsonCreator
    public static GenderRO forValue(String id) {
        return gendersMap.get(id);
    }
}
