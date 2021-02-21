package com.online.loja.api.endpoint;

import com.online.loja.api.mapper.ProductMapper;
import com.online.loja.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Integration test to create product when successful")
    void when_create_product_return_created_product() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProductById() {
    }
}