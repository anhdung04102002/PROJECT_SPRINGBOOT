package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
