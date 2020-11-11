package com.online.loja.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResponse {
    private final String email;
    private final String name;
    private final String jwttoken;

    public LoginResponse(String email, String name, String jwttoken) {
        this.email = email;
        this.name = name;
        this.jwttoken = jwttoken;
    }
}
