package com.online.loja.service;

import com.online.loja.repository.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    List<Product> findAllProductsAvailable();

    Optional<Product> findProductById(Long id);

    Optional<Product> updateProduct(Product product);

    Optional<Boolean> deleteProductById(Long id);

    Product createProduct(Product product);

    List<Product> findAllProductsById(Iterable<Long> productsId);


}
