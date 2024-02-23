package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Integer> {
}
