package com.filiaiev.authservice.service;

import com.filiaiev.authservice.exception.AuthenticationException;
import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.user.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        UserDO userDO = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userMapper.mapUserDOToUser(userDO);
    }
}
