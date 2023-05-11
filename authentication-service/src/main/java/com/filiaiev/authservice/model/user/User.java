package com.filiaiev.authservice.model.user;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String email;
    private UserDetails userDetails;
}
