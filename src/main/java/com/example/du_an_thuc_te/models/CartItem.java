package com.example.du_an_thuc_te.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column(name = "so_luong")

    private int soLuong;
    @Column(name = "gia_san_pham")

    private double giaSanPham;
    @Column(name = "tong_gia_moi_san_pham")

    private double tongGiaMoiSanPham;
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public CartItem(ShoppingCart shoppingCart,Product product, int soLuong) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.soLuong = soLuong;
        this.giaSanPham = product.getPrice();
        this.tongGiaMoiSanPham = tinhTong();
    }

    public CartItem() {
    }

    private double tinhTong() {
        return soLuong * giaSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public double getTongGiaMoiSanPham() {
        return tongGiaMoiSanPham;
    }

    public void setTongGiaMoiSanPham(double tongGiaMoiSanPham) {
        this.tongGiaMoiSanPham = tongGiaMoiSanPham;
    }

}
