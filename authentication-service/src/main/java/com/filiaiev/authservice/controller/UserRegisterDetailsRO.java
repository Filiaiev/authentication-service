package com.filiaiev.authservice.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegisterDetailsRO {

    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatPassword;
}
