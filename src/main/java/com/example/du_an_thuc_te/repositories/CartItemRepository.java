package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
