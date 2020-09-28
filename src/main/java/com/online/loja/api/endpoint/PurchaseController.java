package com.online.loja.api.endpoint;

import com.online.loja.api.mapper.PurchaseMapper;
import com.online.loja.endpoint.PurchasesApi;
import com.online.loja.model.PurchaseRequest;
import com.online.loja.model.PurchaseResponse;
import com.online.loja.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PurchaseController implements PurchasesApi {

    private final PurchaseMapper purchaseMapper;
    private final PurchaseService purchaseService;


    @Autowired
    public PurchaseController(PurchaseMapper purchaseMapper, PurchaseService purchaseService) {
        this.purchaseMapper = purchaseMapper;
        this.purchaseService = purchaseService;
    }

    @Override
    public ResponseEntity<PurchaseResponse> createPurchase(@Valid PurchaseRequest purchaseRequest) {
        var purchase = purchaseMapper.toPurchaseEntity(purchaseRequest);
        var purchaseResponse = purchaseMapper.toPurchaseResponse(purchaseService.createOrder(purchase));
        return ResponseEntity.ok(purchaseResponse);
    }

    @Override
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases() {
        return ResponseEntity
                .ok(purchaseService.getAllOrders()
                        .stream()
                        .map(purchaseMapper::toPurchaseResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<PurchaseResponse> getPurchaseById(Long purchaseId) {
        return purchaseService
                .getOrderById(purchaseId)
                .map(purchaseMapper::toPurchaseResponse)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
