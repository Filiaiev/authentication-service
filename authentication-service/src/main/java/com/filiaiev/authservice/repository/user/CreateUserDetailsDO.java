package com.filiaiev.authservice.repository.user;

import com.filiaiev.authservice.model.user.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDetailsDO {

    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private GenderDO gender;
    private LocalDate dob;
    private String mobile;
    private String city;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
}
