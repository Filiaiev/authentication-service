package com.filiaiev.authservice.repository;

import com.filiaiev.authservice.model.user.CreateUserDetails;
import com.filiaiev.authservice.repository.user.CreateUserDetailsDO;
import com.filiaiev.authservice.service.mapper.UserMapper;
import io.netty.handler.logging.LogLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;

@Repository
public class UserDetailsRepository extends ApiRepository {

    public static final String USER_DETAILS_PATH = "/users";

    private final UserMapper userMapper;

    public UserDetailsRepository(@Value("${user-details-service.base-url}") String baseUrl,
                                 @Value("${logging.level.webclient}") LogLevel logLevel,
                                 @Autowired UserMapper userMapper) {
        super(baseUrl, logLevel);
        this.userMapper = userMapper;
    }

    public void createUserDetails(CreateUserDetails createUserDetails) {
        CreateUserDetailsDO createUserDetailsDO =
                userMapper.mapCreateUserDetailsToCreateUserDetailsDO(createUserDetails);

        webClient.post().uri(uriBuilder -> uriBuilder
                        .path(USER_DETAILS_PATH)
                        .build()
                )
                .body(BodyInserters.fromValue(createUserDetailsDO))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
