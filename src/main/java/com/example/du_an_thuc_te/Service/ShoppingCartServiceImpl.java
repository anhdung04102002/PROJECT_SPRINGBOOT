package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.CartItem;
import com.example.du_an_thuc_te.repositories.CartItemRepository;
import com.example.du_an_thuc_te.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void deleteItemById(int id) {

    }

    @Override
    public void saveItem(int id) {

    }
}
