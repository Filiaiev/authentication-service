package com.filiaiev.authservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserServiceException extends RuntimeException {

    private final HttpStatus statusCode;

    public UserServiceException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public UserServiceException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public UserServiceException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
}
