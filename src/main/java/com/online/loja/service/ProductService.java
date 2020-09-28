package com.online.loja.service;

import com.online.loja.repository.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Optional<Product> updateProduct(Product product);

    Optional<Boolean> deleteProductById(Long id);

    Product createProduct(Product product);

    List<Product> findAllProductsById(Iterable<Long> productsId);


}
