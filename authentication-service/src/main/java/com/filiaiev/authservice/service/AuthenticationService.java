package com.filiaiev.authservice.service;

import com.filiaiev.authservice.exception.AuthenticationException;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.User;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import com.filiaiev.authservice.model.user.UserTokenDetails;
import com.filiaiev.authservice.repository.UserDetailsRepository;
import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.user.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final JwtGeneratorService jwtGeneratorService;

    public void register(UserRegisterDetails userRegisterDetails) {
        if(userRepository.existsByEmail(userRegisterDetails.getEmail())) {
            throw new AuthenticationException("User already exists", HttpStatus.CONFLICT);
        }

        UserDO userRegisterDO = userMapper.mapUserToUserDO(userRegisterDetails);

        userRepository.save(userRegisterDO);

        userDetailsRepository.createUserDetails(userRegisterDetails.getUserDetails());
    }

    public JwtToken authenticate(UserLoginDetails userLoginDetails) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDetails.getEmail(), userLoginDetails.getPassword()
        );

        User authentication = (User) authenticationManager.authenticate(authenticationToken).getPrincipal();
        UserTokenDetails userTokenDetails = userMapper.mapUserToUserTokenDetails(authentication);

        return jwtGeneratorService.generateToken(userTokenDetails);
    }

}
