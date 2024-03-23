package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.ShoppingCartService;
import com.example.du_an_thuc_te.models.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@Controller
public class ShoppingCartController {
    @Autowired
    private  ShoppingCartService shoppingCartService;
    @RequestMapping("/index/cart")
    public String showFormShoppingCart(Model model) {

        return "ShoppingCart";
    }
}
