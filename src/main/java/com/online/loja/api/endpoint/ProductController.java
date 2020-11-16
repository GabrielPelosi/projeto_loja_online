package com.online.loja.api.endpoint;

import com.online.loja.api.mapper.ProductMapper;
import com.online.loja.endpoint.ProductsApi;
import com.online.loja.model.ProductRequest;
import com.online.loja.model.ProductResponse;
import com.online.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.loja.security.config.SecurityConstants.HAS_ADMIN_ROLE;

@RestController
@CrossOrigin
public class ProductController implements ProductsApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }



    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        var product = productMapper.toProductEntity(productRequest);
        var productResponse = productMapper.toProductResponse(productService.createProduct(product));
        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity<Void> deleteProductById(Long productId) {
        return productService.deleteProductById(productId)
                .map(res -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProductsAvailable()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long productId) {
        return productService.findProductById(productId)
                .map(productMapper::toProductResponse)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ProductResponse> updateProductById(Long productId, ProductRequest productRequest) {

        var product = productMapper.toProductEntity(productRequest);
        product.setId(productId);
        return productService.updateProduct(product)
                .map(productMapper::toProductResponse)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
