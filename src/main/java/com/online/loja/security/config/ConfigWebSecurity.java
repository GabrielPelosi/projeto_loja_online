package com.online.loja.security.config;

import com.online.loja.security.config.jwt.JwtAuthenticationEntryPoint;
import com.online.loja.security.config.jwt.JwtRequestFilter;
import com.online.loja.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.online.loja.security.config.SecurityConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET,PRODUCTS_AVAILABLE_URL).permitAll()
                .antMatchers(HttpMethod.GET,CATEGORY_GET_URL).permitAll()
                .antMatchers(HttpMethod.POST,LOGIN_URL).permitAll()

                .antMatchers(HttpMethod.PUT,"/purchases").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE,"/purchases").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.GET,"/purchases").hasRole(HAS_ADMIN_ROLE)

                .antMatchers(HttpMethod.POST,"/purchases").hasRole(HAS_USER_ROLE)

                .antMatchers(HttpMethod.PUT,"/categories").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE,"/categories").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.POST,"/categories").hasRole(HAS_ADMIN_ROLE)

                .antMatchers(HttpMethod.PUT,"/products").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE,"/products").hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.POST,"/products").hasRole(HAS_ADMIN_ROLE)

                .antMatchers(HttpMethod.POST,"/purchases").hasRole(HAS_USER_ROLE)
                .antMatchers(HttpMethod.GET,"/purchases-user").hasRole(HAS_USER_ROLE)


                .antMatchers(HttpMethod.GET,PROFILE_ADMIN_URL).hasRole(HAS_ADMIN_ROLE)
                .antMatchers(HttpMethod.POST,PROFILE_URL).hasRole(HAS_USER_ROLE)



                .antMatchers("/v2/api-docs").hasRole(HAS_ADMIN_ROLE)

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
