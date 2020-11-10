package com.online.loja.security.service;

import com.online.loja.exception.UsernameAlreadyExistsException;
import com.online.loja.security.entity.User;
import com.online.loja.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email).get();
        if (user == null)
            throw new UsernameNotFoundException("Usuário não encontrado!");
        return user;
    }


    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new UsernameAlreadyExistsException("Email encontra-se em uso!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}