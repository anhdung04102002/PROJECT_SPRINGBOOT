package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.CategoryService;
import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Service
public class homePage {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/index")
    public  String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo",defaultValue = "1") int pageNo){
        List<Categories> categories = categoryService.getAll();
        List<Product> products = productService.getAll();
        List<Product> sanPhamNoiBat = new ArrayList<>();
        for(Product product: products) {
            if(product.getCategories().getCategoryName().equals("Sản phẩm nổi bật")) {
                sanPhamNoiBat.add(product);
            }
        }
        model.addAttribute("product",sanPhamNoiBat);
        return "index";
    }
}
