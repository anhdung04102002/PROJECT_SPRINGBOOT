package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
}
