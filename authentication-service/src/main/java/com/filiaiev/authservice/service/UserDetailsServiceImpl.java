package com.filiaiev.authservice.service;

import com.filiaiev.authservice.repository.UserRepository;
import com.filiaiev.authservice.repository.user.UserDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDO> userDO = userRepository.findBySimpleNaturalId(email);
        return userMapper.mapUserDOToUser(userDO.orElseThrow());
    }
}
