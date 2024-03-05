package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void saveProduct(Product product);
    Product getProduct(int id);
    void deleteProduct(int id);
}