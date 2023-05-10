package com.filiaiev.authservice.resource.mapper;

import com.filiaiev.authservice.model.user.CreateUserDetails;
import com.filiaiev.authservice.resource.entity.CreateUserDetailsRO;
import com.filiaiev.authservice.resource.entity.JwtTokenRO;
import com.filiaiev.authservice.resource.entity.UserLoginDetailsRO;
import com.filiaiev.authservice.resource.entity.UserRegisterDetailsRO;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class AuthenticationMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract JwtTokenRO mapJwtTokenToJwtTokenRO(JwtToken jwtToken);

    public abstract UserLoginDetails mapUserLoginDetailsROToUserLoginDetails(UserLoginDetailsRO userLoginDetailsRO);

    public UserRegisterDetails mapUserRegisterDetailsROToUserRegisterDetails(UserRegisterDetailsRO userRegisterDetailsRO) {
        CreateUserDetails createUserDetails = mapCreateUserDetailsROToCreateUserDetails(userRegisterDetailsRO.getUserDetails());
        createUserDetails.setEmail(userRegisterDetailsRO.getEmail());

        return UserRegisterDetails.builder()
                .email(userRegisterDetailsRO.getEmail())
                .password(passwordEncoder.encode(userRegisterDetailsRO.getPassword()))
                .userDetails(createUserDetails)
                .build();
    }

    public abstract CreateUserDetails mapCreateUserDetailsROToCreateUserDetails(CreateUserDetailsRO createUserDetailsRO);
}
