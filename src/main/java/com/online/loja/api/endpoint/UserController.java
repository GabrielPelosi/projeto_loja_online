package com.online.loja.api.endpoint;


import com.online.loja.exception.UserAlreadyExistsException;
import com.online.loja.security.confirmationToken.service.ConfirmationTokenService;
import com.online.loja.security.entity.User;
import com.online.loja.service.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;


    @PostMapping("/sign-up")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user){
            User savedUser = userService.signUpUser(user);
            return ResponseEntity.ok(savedUser);
    }


    /*
    @PostMapping()
    public String confirmUserEmail(@RequestBody @Valid User user){

    }

     */


}
