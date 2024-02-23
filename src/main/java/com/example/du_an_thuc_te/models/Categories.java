package com.example.du_an_thuc_te.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "categoryName")
    private String categoryName;
    @Column(name = "categoryStatus")
    private Boolean categoryStatus;

    public Categories() {
    }

    public Categories(int id, String categoryName, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public boolean getCategoryStatus() {
        return categoryStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
