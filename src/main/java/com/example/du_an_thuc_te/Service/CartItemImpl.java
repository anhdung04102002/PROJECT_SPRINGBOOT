package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.CartItem;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Controller

public class CartItemImpl implements  CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public void saveCartItem(CartItem cartItem) {
        this.cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(int id) {
        Optional<CartItem> optinonal = cartItemRepository.findById(id);
        CartItem cartItem = null;
        if(optinonal.isPresent()) {
            cartItem = optinonal.get();
        }
        else {
            System.out.println("CartItem not found");
        }
        return cartItem;

    }
}
