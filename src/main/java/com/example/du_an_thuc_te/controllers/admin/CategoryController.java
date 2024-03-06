package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.CategoryService;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Service
public class CategoryController {
    @Autowired
    private CategoryService categoryService; // thông qua lớp giao diện

    @RequestMapping("/admin/category")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Page<Categories> list = this.categoryService.getAll(pageNo);
            if (keyword != null && !keyword.isEmpty()) {
                list = this.categoryService.searchCategory(keyword,pageNo);
                model.addAttribute("keyword",keyword);
            }

        model.addAttribute("list", list);
        model.addAttribute("totalPage", list.getTotalPages()); //tong so trang
        model.addAttribute("currentPage", pageNo);

//        model.addAttribute("list",list);
        return "admin/category/index";
    }

    // phải request đến trang add trước rồi khi vào đó mới thực hiện hành động post được
    @RequestMapping("/admin/add-category")
    public String add(Model model) {
        Categories categories = new Categories();
        // cài sẵn trường cho status là true (hiện) để khi vào có sẵn tích ở hiện
        categories.setCategoryStatus(true);
        model.addAttribute("category", categories);
        return "admin/category/add";
    }

    @PostMapping("/admin/add-category/saveCategories")
    public String saveCategory(@ModelAttribute("category") Categories categories) {
        categoryService.saveCategories(categories);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        this.categoryService.deleteCategories(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model) {
        Categories categories = categoryService.getCategoriesById(id);
        model.addAttribute("category", categories);
        return "admin/category/update";
    }

    @PostMapping("/admin/categogy/update")
    public String saveUpdate(@ModelAttribute("categoryUpdate") Categories categories) {
        categoryService.saveCategories(categories);
        return "redirect:/admin/category";
    }

}
