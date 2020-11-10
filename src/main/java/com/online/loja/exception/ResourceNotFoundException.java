package com.online.loja.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource) {
        super(String.format("Resource %s not found", resource));
    }

    public ResourceNotFoundException(String resource, String a, Long ab) {
        super(String.format("Resource %s not found", resource + a + ab));
    }
}
