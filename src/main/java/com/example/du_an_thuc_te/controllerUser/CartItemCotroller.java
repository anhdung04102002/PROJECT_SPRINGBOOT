package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.CartItemService;
import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.Service.ShoppingCartService;
import com.example.du_an_thuc_te.models.CartItem;
import com.example.du_an_thuc_te.models.Product;
import com.example.du_an_thuc_te.models.ShoppingCart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Controller
public class CartItemCotroller {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/index/add-cartitem")
    public String cartItem(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        return "ShoppingCart";
    }

    // sử dụng ResponseEntity<Void> để return ResponseEntity.noContent().build()
//    khi sử dụng cái này phản hồi lúc post sẽ đứng im ở vị trí hiện tại mà không bị chuyển trang
    @PostMapping("/index/add-cartitem/save")
    public ResponseEntity<Void> add(@RequestParam("productId") int productId, HttpServletRequest request, HttpServletResponse response, Model model) {
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

    @RequestMapping("/index/backindex")
    public String backHome() {
        return "redirect:/index";
    }

    @RequestMapping("/index/add-cartitem/success")
    public String pay(HttpServletRequest request, Model model) {
        // Lấy thông tin giỏ hàng từ session
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            // Xử lý khi không có giỏ hàng trong session
            return "redirect:/index";
        }
        // Xử lý thanh toán và các hoạt động khác liên quan
        // Sau khi xử lý thành công, bạn có thể xóa giỏ hàng từ session nếu cần
        session.removeAttribute("shoppingCart");
        return "PaymentSuccess";
    }


}
