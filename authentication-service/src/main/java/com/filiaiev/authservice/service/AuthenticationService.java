package com.filiaiev.authservice.service;

import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.User;
import com.filiaiev.authservice.model.user.UserLoginDetails;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import com.filiaiev.authservice.model.user.UserTokenDetails;
import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.user.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGeneratorService jwtGeneratorService;
    private final UserMapper userMapper;

    public void register(UserRegisterDetails userRegisterDetails) {
        if(userRepository.existsByEmail(userRegisterDetails.getEmail())) {
            throw new EntityExistsException("User already exists");
        }

        UserDO userRegisterDO = userMapper.mapUserToUserDO(userRegisterDetails);
        userRepository.save(userRegisterDO);
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
