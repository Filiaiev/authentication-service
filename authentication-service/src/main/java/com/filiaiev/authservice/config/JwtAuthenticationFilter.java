package com.filiaiev.authservice.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.filiaiev.authservice.model.user.Principal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String CLAIM_ROLE_KEY = "role";
    private static final String CLAIM_USER_ID_KEY = "userId";
    private static final String CLAIM_USER_EMAIL_KEY = "userEmail";

    private final JWTVerifier jwtVerifier;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            String token = authHeader.substring(7);

            DecodedJWT decodedJwt = jwtVerifier.verify(token);

            SecurityContextHolder.getContext().setAuthentication(

                    new UsernamePasswordAuthenticationToken(
                            new Principal(decodedJwt.getClaim(CLAIM_USER_ID_KEY).asInt(),
                                    decodedJwt.getClaim(CLAIM_USER_EMAIL_KEY).asString()
                            ),
                            "",
                            Collections.singleton(new SimpleGrantedAuthority(decodedJwt.getClaim(CLAIM_ROLE_KEY).asString()))
                    )
            );
        }

        filterChain.doFilter(request, response);
    }
}
