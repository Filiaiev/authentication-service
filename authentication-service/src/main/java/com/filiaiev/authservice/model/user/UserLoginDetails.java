package com.filiaiev.authservice.model.user;

import lombok.Data;

@Data
public class UserLoginDetails {

    private String email;
    private String password;
}
