package com.online.loja.service;


import com.online.loja.repository.entity.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {


    List<Purchase> getAllOrders();

    Optional<Purchase> getOrderById(Long id);

    Optional<Purchase> updateOrder(Purchase purchase);

    Optional<Boolean> deleteOrderById(Long id);

    Purchase createOrder(Purchase purchase);

    List<Purchase> getAllPurchasesByEmailUser(String emailUser);
}
