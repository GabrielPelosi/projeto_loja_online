package com.online.loja.api.exception;

import com.online.loja.exception.EmailAlreadyExistsException;
import com.online.loja.exception.UsernameAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> exceptionHandlerUserAlreadyRegistrated(UsernameAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> exceptionHandlerUserNotFound(UsernameNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> exceptionHandlerEmailAlreadyExists(EmailAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
