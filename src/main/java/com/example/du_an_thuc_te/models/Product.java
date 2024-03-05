package com.example.du_an_thuc_te.models;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productPrice")
    private Double price;
    @Column(name = "productImage")
    private String image;
    @Column(name = "productDescription")
    private String description;
    @ManyToOne()
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Categories categories;

    public Product(int id, String productName, Double price, String image, String description, Categories categories) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.categories = categories;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
