package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// để liên lạc giữa controller và giao diện
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Categories> getAll() {
        return categoryRepository.findAll(); // trả về hết danh sách Categories
    }

    @Override
    public void saveCategories(Categories categories) {
                this.categoryRepository.save(categories);
    }

    @Override
    public Categories getCategoriesById(int id) {
        // optional để tạo đối tượng kiểm tra có null hay không; nó khác == ở chỗ chỉ tìm đối tượng duy nhất
        Optional<Categories> optinonal = categoryRepository.findById(id);
        Categories categories = null;
        if(optinonal.isPresent()) {
            categories = optinonal.get();
        }
        else {
            System.out.println("Category not found");
        }
        return categories;
    }

    @Override
    public void deleteCategories(int id) {
       this.categoryRepository.deleteById(id);
    }
}
