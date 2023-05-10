package com.filiaiev.authservice.model.user;

import lombok.Data;

@Data
public class UserTokenDetails {

    private Integer id;
    private String email;
    private UserRole role;
}
