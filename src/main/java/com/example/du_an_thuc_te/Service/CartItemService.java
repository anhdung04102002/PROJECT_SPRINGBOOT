package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.CartItem;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public interface CartItemService {
    void saveCartItem(CartItem cartItem);
    List<CartItem> getAll();
    CartItem getCartItemById(int id) ;
}
