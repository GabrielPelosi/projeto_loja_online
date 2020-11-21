package com.online.loja.payload;

import com.online.loja.repository.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@Slf4j
public class RegisterRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    private String address;

    @NotNull
    private String neighborhood;

    @NotNull
    private String postalCode;

    @NotNull
    private String city;

    @NotNull
    private String state;


}
