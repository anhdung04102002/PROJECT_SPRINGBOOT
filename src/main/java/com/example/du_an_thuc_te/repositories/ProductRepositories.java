package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product,Integer> {
    @Query("select c from Product c where  c.productName like %?1%")
    List<Product> searchProduct(String keyword);
}
