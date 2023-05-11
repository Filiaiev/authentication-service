package com.filiaiev.authservice.service;

import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.entity.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        UserDO userDO = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("UserAuth not found"));

        return userMapper.mapUserDOToUserAuth(userDO);
    }
}
