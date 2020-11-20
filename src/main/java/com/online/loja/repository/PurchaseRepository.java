package com.online.loja.repository;


import com.online.loja.repository.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {


    List<Purchase> findByEmailUser(String emailUser);
}
