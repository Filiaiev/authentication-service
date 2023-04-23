package com.filiaiev.authservice.model.user;

import lombok.Data;

@Data
public class UserTokenDetails {

    private long id;
    private String email;
    private UserRole role;
}
