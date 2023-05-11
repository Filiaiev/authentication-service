package com.filiaiev.authservice.resource.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserRO {

    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password should be at least eight characters, including one uppercase letter, one lowercase letter and one number")
    private String password;

    private CreateUserDetailsRO userDetails;
}
