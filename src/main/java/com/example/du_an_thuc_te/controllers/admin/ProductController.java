package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.CategoryService;
import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.Service.StorageService;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Service
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    //lấy danh mục của sản phẩm thì phải thêm danh mục
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;


    @RequestMapping("/product")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo",defaultValue = "1") int pageNo) { //đặt mặc định trang đầu là 1
        Page<Product> list = productService.getAll(pageNo);
        if (keyword != null && !keyword.isEmpty()) {
            list = this.productService.searchProduct(keyword,pageNo);
            model.addAttribute("keyword",keyword);
        }
        model.addAttribute("list", list);
        model.addAttribute("totalPage",list.getTotalPages()); //tong so trang
        model.addAttribute("currentPage",pageNo);
        return "admin/product/index";
    }

    @RequestMapping("/add-product")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Categories> listCate = categoryService.getAll();
        model.addAttribute("listCate", listCate);
        return "admin/product/add";
    }

    @PostMapping("/add-product/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
        // Implement error handling for potential exceptions
        try {
            this.storageService.store(file);
            String fileName = file.getOriginalFilename();
            product.setImage(fileName);
            productService.saveProduct(product);
            return "redirect:/admin/product";
        } catch (Exception e) {
            // Handle exception (e.g., log the error, display a user-friendly message)
            return "redirect:/admin/product/add"; // Or handle error page redirection
        }
    }
    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/product/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product",product);
        List<Categories> listCate = categoryService.getAll();
        model.addAttribute("listCate", listCate);
        return "admin/product/update";
    }
    @PostMapping("/product/update")
    public  String saveUpdate(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            boolean isEmpty = fileName == null || fileName.trim().length() == 0;
            if(!isEmpty) {
                //upload file
                this.storageService.store(file);
                product.setImage(fileName);
            }
            productService.saveProduct(product);
            return "redirect:/admin/product";
        } catch (Exception e) {
            // Handle exception (e.g., log the error, display a user-friendly message)
            return "redirect:/admin/product"; // Or handle error page redirection
        }
    }

}
