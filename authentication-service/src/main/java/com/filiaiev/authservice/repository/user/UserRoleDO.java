package com.filiaiev.authservice.repository.user;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum UserRoleDO {

    CUSTOMER("customer"),
    MANAGER("manager"),
    ADMIN("admin");

    public final String value;

    private static final Map<String, UserRoleDO> rolesMap = Stream.of(UserRoleDO.values())
            .collect(Collectors.toMap(role -> role.value, Function.identity()));

    public static UserRoleDO forValue(String id) {
        return rolesMap.get(id);
    }
}
