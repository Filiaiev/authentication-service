package com.filiaiev.authservice.resource.mapper;

import com.filiaiev.authservice.model.user.CreateUser;
import com.filiaiev.authservice.model.user.CreateUserDetails;
import com.filiaiev.authservice.model.user.User;
import com.filiaiev.authservice.resource.entity.*;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserResourceMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract JwtTokenRO mapJwtTokenToJwtTokenRO(JwtToken jwtToken);

    public abstract UserLoginDetails mapUserLoginDetailsROToUserLoginDetails(UserLoginDetailsRO userLoginDetailsRO);

    public CreateUser mapUserRegisterDetailsROToUserRegisterDetails(CreateUserRO userRegisterDetailsRO) {
        CreateUserDetails createUserDetails = mapCreateUserDetailsROToCreateUserDetails(userRegisterDetailsRO.getUserDetails());

        return CreateUser.builder()
                .email(userRegisterDetailsRO.getEmail())
                .password(passwordEncoder.encode(userRegisterDetailsRO.getPassword()))
                .userDetails(createUserDetails)
                .build();
    }

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    public abstract CreateUserDetails mapCreateUserDetailsROToCreateUserDetails(CreateUserDetailsRO createUserDetailsRO);

    @Mapping(target = "details", source = "userDetails")
    public abstract UserRO mapUserToUserRO(User user);
}
