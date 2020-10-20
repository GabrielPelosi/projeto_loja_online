package com.online.loja.exception;

import org.springframework.security.core.AuthenticationException;


public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
