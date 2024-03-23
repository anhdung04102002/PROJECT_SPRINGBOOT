package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.models.CartItem;
import com.example.du_an_thuc_te.models.Product;
import com.example.du_an_thuc_te.models.ShoppingCart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Controller
public class productDetail {
    @Autowired
    private ProductService productService;
    @RequestMapping("/index/product/{id}")
    public String product(Model model, @PathVariable("id") int productId) {
        Product productDetailid =  productService.getProduct(productId);
        model.addAttribute("productDetail",productDetailid);
        return "product";
    }
    @PostMapping("/index/add-product/save")
    public ResponseEntity<Void> addProduct(@RequestParam("productId") int productId, HttpServletRequest request, HttpServletResponse response, Model model){
        // Lấy thông tin sản phẩm từ cơ sở dữ liệu

        Product product = productService.getProduct(productId);
        // Lấy giỏ hàng hiện có từ session hoặc tạo mới nếu chưa tồn tại
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        // Thêm sản phẩm vào giỏ hàng
        CartItem cartItem = new CartItem(shoppingCart, product, 1);
        shoppingCart.getCartItems().add(cartItem);
        // Tính tổng số tiền thanh toán cho giỏ hàng
        double tongTienThanhToan = shoppingCart.getCartItems().stream()
                .mapToDouble(CartItem::getTongGiaMoiSanPham)
                .sum();
        shoppingCart.setTongTienThanhToan(tongTienThanhToan);
        // Lưu giỏ hàng đã cập nhật vào session (sẽ tự động lưu vào cookie)
        session.setAttribute("shoppingCart", shoppingCart);
        // Chuyển hướng người dùng đến trang giỏ hàng
        return ResponseEntity.noContent().build();
    }
}
