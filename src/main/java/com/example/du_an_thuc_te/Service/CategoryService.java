package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;

import java.util.List;

public interface CategoryService {
    List<Categories> getAll();
    void saveCategories(Categories categories);
    Categories getCategoriesById(int id);
    void deleteCategories(int id);
}
