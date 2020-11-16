package com.online.loja.security.config;

public class SecurityConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/register";

    public static final String PRODUCTS_AVAILABLE_URL = "/products";
    public static final String LOGIN_URL = "/users/login";
    public static final String HAS_ADMIN_ROLE = "ROLE_ADMIN";
    public static final String HAS_USER_ROLE = "ROLE_USER";

}
