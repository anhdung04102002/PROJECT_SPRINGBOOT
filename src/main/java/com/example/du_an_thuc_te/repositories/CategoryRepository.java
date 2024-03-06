package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.Categories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories,Integer> {
    @Query("select c from Categories c where  c.categoryName like %?1%")
    List<Categories> searchCategories(String keyword);


}
