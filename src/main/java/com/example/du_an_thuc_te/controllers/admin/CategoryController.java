package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.CategoryService;
import com.example.du_an_thuc_te.models.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService; // thông qua lớp giao diện
    @RequestMapping("/admin/category")
    public String index(Model model) {
        List<Categories> list = categoryService.getAll();
        model.addAttribute("list",list);
        return "admin/category/index";
    }
    @RequestMapping("/admin/add-category")
    public String add() {
        return "admin/category/add";
    }

}
