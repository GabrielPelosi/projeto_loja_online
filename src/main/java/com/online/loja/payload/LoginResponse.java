package com.online.loja.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResponse {
    private final String jwttoken;

    public LoginResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

}
