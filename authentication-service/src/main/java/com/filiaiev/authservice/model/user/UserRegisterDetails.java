package com.filiaiev.authservice.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterDetails {

    private String email;
    private String password;
    private CreateUserDetails userDetails;
}
