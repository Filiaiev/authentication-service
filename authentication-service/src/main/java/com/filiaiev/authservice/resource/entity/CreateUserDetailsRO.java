package com.filiaiev.authservice.resource.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDetailsRO {

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
