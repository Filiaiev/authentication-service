package com.filiaiev.authservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "user_details")
@Data
public class UserDetailsDO {

    @Id
    @JoinColumn(name = "user_id")
    @OneToOne
    private UserDO user;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private GenderDO gender;
    private String mobile;
    private String city;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private Instant createdAt;
}
