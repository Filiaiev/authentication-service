package com.filiaiev.authservice.service;

import com.filiaiev.authservice.exception.UserServiceException;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.*;
import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.entity.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final JwtGeneratorService jwtGeneratorService;

    public void createUser(CreateUser createUser) {
        if(userRepository.existsByEmail(createUser.getEmail())) {
            throw new UserServiceException("User already exists", HttpStatus.CONFLICT);
        }

        UserDO userRegisterDO = userMapper.mapUserRegisterDetailsToUserDO(createUser);

        userRepository.save(userRegisterDO);
    }

    public JwtToken authenticateUser(UserLoginDetails userLoginDetails) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDetails.getEmail(), userLoginDetails.getPassword()
        );

        UserAuth authentication = (UserAuth) authenticationManager.authenticate(authenticationToken).getPrincipal();
        UserTokenDetails userTokenDetails = userMapper.mapUserToUserTokenDetails(authentication);

        return jwtGeneratorService.generateToken(userTokenDetails);
    }

    public User getUserDetails(Integer id) {
        UserDO userDO = userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException("User not found", HttpStatus.NOT_FOUND));

        return userMapper.mapUserDOToUser(userDO);
    }

}
