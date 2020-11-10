package com.online.loja.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@Slf4j
public class LoginRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;

}
