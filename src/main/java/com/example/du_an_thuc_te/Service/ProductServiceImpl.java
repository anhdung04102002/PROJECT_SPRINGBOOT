package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import com.example.du_an_thuc_te.repositories.ProductRepositories;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepositories productRepositories;

    @Override
    public List<Product> getAll() {
        return this.productRepositories.findAll();
    }

    @Override
    public void saveProduct(Product Product) {
        this.productRepositories.save(Product);
    }

    @Override
    public Product getProduct(int id) {
        Optional<Product> optional = productRepositories.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            System.out.println("Product not found");
        }
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        this.productRepositories.deleteById(id);
    }

    @Override
    public Page<Product> getAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        return this.productRepositories.findAll(pageable);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
       return this.productRepositories.searchProduct(keyword);
    }

    @Override
    public Page<Product> searchProduct(String keyword, int pageNo) {
        List<Product> list = this.searchProduct(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        int totalItems = list.size();

        if (totalItems == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            int start = (int) pageable.getOffset();
            int end = (int) Math.min((start + pageable.getPageSize()), totalItems);
            List<Product> sublist = list.subList(start, end);
            return new PageImpl<>(sublist, pageable, totalItems);
        }
    }


}
