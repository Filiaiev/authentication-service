package com.filiaiev.authservice.resource.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.authservice.model.user.Gender;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDetailsRO {

    private String firstName;
    private String lastName;
    private String middleName;
    private GenderRO gender;
    private LocalDate dob;
    private String mobile;
    private String city;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
}
