package com.example.demo.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {
    public CaptchaException(String errorMessage) {
        super(errorMessage);
    }
}
