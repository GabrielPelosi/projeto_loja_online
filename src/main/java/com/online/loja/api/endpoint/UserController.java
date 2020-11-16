package com.online.loja.api.endpoint;

import com.online.loja.payload.LoginRequest;
import com.online.loja.payload.LoginResponse;
import com.online.loja.payload.RegisterRequest;
import com.online.loja.security.config.SecurityConstants;
import com.online.loja.security.config.jwt.JwtTokenUtil;
import com.online.loja.security.entity.User;
import com.online.loja.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(SecurityConstants.HAS_USER_ROLE)
                .emailVerified(false)
                .build();
        return ResponseEntity.ok(userDetailsService.registerUser(user));
    }


    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest) throws Exception{

        authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        final User userDetails =
                (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(userDetails.getName(),userDetails.getEmail(),token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
