package com.online.loja.api.mapper;


import com.online.loja.model.PurchaseRequest;
import com.online.loja.model.PurchaseResponse;
import com.online.loja.repository.entity.Purchase;
import com.online.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    private  final ProductMapper productMapper;

    private final ProductService productService;

    @Autowired
    public PurchaseMapper(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    public Purchase toPurchaseEntity(PurchaseRequest purchaseRequest){
        return Purchase.builder().id(null)
                .status(purchaseRequest.getStatus())
                .price(purchaseRequest.getPrice())
                .products(productService.findAllProductsById(purchaseRequest.getProductsId()))
                .build();
    }


    public PurchaseResponse toPurchaseResponse(Purchase purchase){
        return new PurchaseResponse()
                .id(purchase.getId())
                .price(purchase.getPrice())
                .status(purchase.getStatus())
                .products(productMapper.toProductResponseList(purchase.getProducts()));
    }
}
