package com.filiaiev.authservice.controller;

import com.filiaiev.authservice.controller.mapper.AuthenticationMapper;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import com.filiaiev.authservice.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationMapper authenticationMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegisterDetailsRO userRegisterDetailsRO) {
        UserRegisterDetails userRegisterDetails = authenticationMapper.mapUserRegisterDetailsROToUserRegisterDetails(userRegisterDetailsRO);

        authenticationService.register(userRegisterDetails);
    }

    @PostMapping("/token")
    public JwtTokenRO authenticate(@RequestBody UserLoginDetailsRO userLoginDetailsRO) {
        UserLoginDetails userLoginDetails =
                authenticationMapper.mapUserLoginDetailsROToUserLoginDetails(userLoginDetailsRO);

        JwtToken generatedToken = authenticationService.authenticate(userLoginDetails);

        return authenticationMapper.mapJwtTokenToJwtTokenRO(generatedToken);
    }

}
