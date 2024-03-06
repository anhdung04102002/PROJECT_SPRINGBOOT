package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import com.example.du_an_thuc_te.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Categories> searchCategory(String keyword) {
       return this.categoryRepository.searchCategories(keyword);
    }

    @Override
    public Page<Categories> getAll(int pageNo) {
         Pageable pageable = PageRequest.of(pageNo - 1, 3);
        return this.categoryRepository.findAll(pageable);    }

    @Override
    public Page<Categories> searchCategory(String keyword, int pageNo) {

        List<Categories> list = this.searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        int totalItems = list.size();

        if (totalItems == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            int start = (int) pageable.getOffset();
            int end = (int) Math.min((start + pageable.getPageSize()), totalItems);
            List<Categories> sublist = list.subList(start, end);
            return new PageImpl<>(sublist, pageable, totalItems);
        }
    }
}
