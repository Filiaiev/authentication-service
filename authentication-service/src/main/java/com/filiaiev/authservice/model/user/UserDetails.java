package com.filiaiev.authservice.model.user;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class UserDetails {

    private Integer userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String mobile;
    private String city;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private Instant createdAt;
}
