package com.filiaiev.authservice.resource;

import com.filiaiev.authservice.model.user.CreateUser;
import com.filiaiev.authservice.resource.entity.JwtTokenRO;
import com.filiaiev.authservice.resource.entity.UserLoginDetailsRO;
import com.filiaiev.authservice.resource.entity.CreateUserRO;
import com.filiaiev.authservice.resource.entity.UserRO;
import com.filiaiev.authservice.resource.mapper.UserResourceMapper;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserResourceMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserRO createUserRO) {
        CreateUser userRegisterDetails = userMapper.
                mapUserRegisterDetailsROToUserRegisterDetails(createUserRO);

        userService.createUser(userRegisterDetails);
    }

    @PostMapping("/auth")
    public JwtTokenRO authenticateUser(@RequestBody UserLoginDetailsRO userLoginDetailsRO) {
        UserLoginDetails userLoginDetails =
                userMapper.mapUserLoginDetailsROToUserLoginDetails(userLoginDetailsRO);

        JwtToken generatedToken = userService.authenticateUser(userLoginDetails);

        return userMapper.mapJwtTokenToJwtTokenRO(generatedToken);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN') or hasRole('CUSTOMER') and principal.id == #id")
    public UserRO getUserDetails(@PathVariable Integer id) {
        return userMapper.mapUserToUserRO(
                userService.getUserDetails(id)
        );
    }
}
