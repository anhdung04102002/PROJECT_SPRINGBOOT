package com.example.du_an_thuc_te.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
    @RequestMapping("/admin/category")
    public String Category() {
        return "admin/category/index";
    }
}
