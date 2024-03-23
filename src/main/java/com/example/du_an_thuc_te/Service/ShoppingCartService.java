package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.CartItem;

import java.util.List;

public interface ShoppingCartService {
    List<CartItem> getAll();
    void deleteItemById(int id);
    void saveItem(int id);
}
