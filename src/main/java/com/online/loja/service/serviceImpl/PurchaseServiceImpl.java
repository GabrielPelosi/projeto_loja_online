package com.online.loja.service.serviceImpl;


import com.online.loja.repository.PurchaseRepository;
import com.online.loja.repository.entity.Purchase;
import com.online.loja.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Purchase> getAllOrders() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> getOrderById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase createOrder(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Optional<Purchase> updateOrder(Purchase purchase) {
        return purchaseRepository.findById(purchase.getId())
                .map(p -> purchase)
                .map(purchaseRepository::save);
    }

    @Override
    public Optional<Boolean> deleteOrderById(Long id) {
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    purchaseRepository.delete(purchase);
                    return true;
                });

    }
}
