package com.filiaiev.authservice.service.mapper;

import com.filiaiev.authservice.model.user.*;
import com.filiaiev.authservice.repository.entity.UserDO;
import com.filiaiev.authservice.repository.entity.UserDetailsDO;
import com.filiaiev.authservice.repository.entity.UserRoleDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserTokenDetails mapUserToUserTokenDetails(UserAuth user);

    UserAuth mapUserDOToUserAuth(UserDO userDO);

    User mapUserDOToUser(UserDO userDO);

    default UserDO mapUserRegisterDetailsToUserDO(CreateUser userRegisterDetails) {
        UserDetailsDO userDetailsDO = mapCreateUserDetailsToUserDetailsDO(userRegisterDetails.getUserDetails());

        UserDO userDO = UserDO.builder()
                .email(userRegisterDetails.getEmail())
                .password(userRegisterDetails.getPassword())
                .role(UserRoleDO.CUSTOMER)
                .userDetails(userDetailsDO)
                .build();

        userDetailsDO.setUser(userDO);

        return userDO;
    }

    UserDetailsDO mapCreateUserDetailsToUserDetailsDO(CreateUserDetails createUserDetails);
}
