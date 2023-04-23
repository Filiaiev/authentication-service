package com.filiaiev.authservice.controller.mapper;

import com.filiaiev.authservice.controller.JwtTokenRO;
import com.filiaiev.authservice.controller.UserLoginDetailsRO;
import com.filiaiev.authservice.controller.UserRegisterDetailsRO;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class AuthenticationMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract JwtTokenRO mapJwtTokenToJwtTokenRO(JwtToken jwtToken);

    public abstract UserLoginDetails mapUserLoginDetailsROToUserLoginDetails(UserLoginDetailsRO userLoginDetailsRO);

    public UserRegisterDetails mapUserRegisterDetailsROToUserRegisterDetails(UserRegisterDetailsRO userRegisterDetailsRO) {
        return UserRegisterDetails.builder()
                .email(userRegisterDetailsRO.getEmail())
                .password(passwordEncoder.encode(userRegisterDetailsRO.getPassword()))
                .build();
    }
}
