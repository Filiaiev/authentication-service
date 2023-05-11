package com.filiaiev.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.filiaiev.authservice.model.JwtToken;
import com.filiaiev.authservice.model.user.UserTokenDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtGeneratorService {

    private static final long TOKEN_VALID_SECONDS = 60 * 30;
    private static final String CLAIM_ROLE_KEY = "role";
    private static final String CLAIM_USER_ID_KEY = "userId";
    private static final String CLAIM_USER_EMAIL = "userEmail";

    @Value("${jwt.secret}")
    private String secret;

    public JwtToken generateToken(UserTokenDetails user) {
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(TOKEN_VALID_SECONDS))
                .withClaim(CLAIM_ROLE_KEY, "ROLE_" + user.getRole().name())
                .withClaim(CLAIM_USER_EMAIL, user.getEmail())
                .withClaim(CLAIM_USER_ID_KEY, user.getId())
                .sign(Algorithm.HMAC512(secret));

        return new JwtToken(token);
    }
}
