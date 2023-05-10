package com.filiaiev.authservice.service.mapper;

import com.filiaiev.authservice.model.user.CreateUserDetails;
import com.filiaiev.authservice.model.user.User;
import com.filiaiev.authservice.model.user.UserRegisterDetails;
import com.filiaiev.authservice.model.user.UserTokenDetails;
import com.filiaiev.authservice.repository.user.CreateUserDetailsDO;
import com.filiaiev.authservice.repository.user.UserDO;
import com.filiaiev.authservice.repository.user.UserRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserTokenDetails mapUserToUserTokenDetails(User user);

    User mapUserDOToUser(UserDO userDO);

    default UserDO mapUserToUserDO(UserRegisterDetails userRegisterDetails) {
        return UserDO.builder()
                .email(userRegisterDetails.getEmail())
                .password(userRegisterDetails.getPassword())
                .role(UserRoleDO.CUSTOMER)
                .build();
    }

    CreateUserDetailsDO mapCreateUserDetailsToCreateUserDetailsDO(CreateUserDetails createUserDetails);
}
