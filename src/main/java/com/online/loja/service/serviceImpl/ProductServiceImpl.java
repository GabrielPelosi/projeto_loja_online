package com.online.loja.service.serviceImpl;


import com.online.loja.repository.ProductRepository;
import com.online.loja.enums.AvailableTypes;
import com.online.loja.repository.entity.Product;
import com.online.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsAvailable() {
        return this.productRepository.findAllByAvailable(AvailableTypes.AVAILABLE.toString());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAllProductsById(Iterable<Long> productsId) {
        return productRepository.findAllById(productsId);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        return productRepository.findById(product.getId())
                .map(p -> product)
                .map(productRepository::save);
    }

    @Override
    public Optional<Boolean> deleteProductById(Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    productRepository.delete(p);
                    return true;
                });
    }


}
