package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositories extends JpaRepository<Product,Integer> {
}
