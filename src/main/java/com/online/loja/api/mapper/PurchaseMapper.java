package com.online.loja.api.mapper;


import com.online.loja.model.Address;
import com.online.loja.model.PurchaseRequest;
import com.online.loja.model.PurchaseResponse;
import com.online.loja.repository.entity.Purchase;
import com.online.loja.service.AddressService;
import com.online.loja.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    private  final ProductMapper productMapper;

    private final ProductService productService;

    private final AddressService addressService;

    @Autowired
    public PurchaseMapper(ProductMapper productMapper, ProductService productService, AddressService addressService) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.addressService = addressService;
    }

    public Purchase toPurchaseEntity(PurchaseRequest purchaseRequest){
        return Purchase.builder().id(null)
                .status(purchaseRequest.getStatus())
                .price(purchaseRequest.getPrice())
                .emailUser(purchaseRequest.getEmailUser())
                .address(addressService.getAddressById(purchaseRequest.getAddressId()).get())
                .products(productService.findAllProductsById(purchaseRequest.getProductsId()))
                .build();
    }


    public PurchaseResponse toPurchaseResponse(Purchase purchase){
        return new PurchaseResponse()
                .id(purchase.getId())
                .price(purchase.getPrice())
                .status(purchase.getStatus())
                .emailUser(purchase.getEmailUser())
                .address(new Address().id(purchase.getAddress().getId())
                        .address(purchase.getAddress().getAddress())
                        .neighborhood(purchase.getAddress().getNeighborhood())
                        .postalCode(purchase.getAddress().getPostalCode())
                        .state(purchase.getAddress().getState())
                        .city(purchase.getAddress().getCity()))
                .products(productMapper.toProductResponseList(purchase.getProducts()));
    }
}
