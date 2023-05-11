package com.filiaiev.authservice.resource.entity;

import lombok.Data;

@Data
public class UserRO {

    private Long id;
    private String email;
    private UserDetailsRO details;
}
