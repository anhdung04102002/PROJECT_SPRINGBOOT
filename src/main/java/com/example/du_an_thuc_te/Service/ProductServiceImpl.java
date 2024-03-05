package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import com.example.du_an_thuc_te.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
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
        if(optional.isPresent()) {
            product = optional.get();
        }
        else {
            System.out.println("Product not found");
        }
        return product;
    }

    @Override
    public void deleteProduct(int id) {
            this.productRepositories.deleteById(id);
    }
}
