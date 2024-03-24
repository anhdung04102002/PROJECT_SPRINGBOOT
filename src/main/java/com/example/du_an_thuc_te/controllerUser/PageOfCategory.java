package com.example.du_an_thuc_te.controllerUser;

import com.example.du_an_thuc_te.Service.CategoryService;
import com.example.du_an_thuc_te.Service.ProductService;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Controller
public class PageOfCategory {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @RequestMapping("/index/category/{id}")
    public String category(@PathVariable("id") int id, Model model, @Param("keyword") String keyword,@RequestParam(name = "pageNo", defaultValue = "1") int pageNo){

        List<Categories> listCategory = categoryService.getAll();
        Categories category = categoryService.getCategoriesById(id);
        Set<Product> setProduct = category.getProducts();
        List<Product> productListAsList = new ArrayList<>(setProduct);
//        Page<Product> listSetProduct = productService.getAll(pageNo);
//        listSetProduct = (Page<Product>) productList; // đặt lại mỗi trang theo danh mục
        int pageSize = 8; // Điều chỉnh theo nhu cầu của bạn
        int totalElements = setProduct.size(); // Hoặc productList.size() nếu không chuyển đổi

        int fromIndex = Math.min((pageNo - 1) * pageSize, productListAsList.size()); //chỉ số bắt đầu trang tiếp theo
        int toIndex = Math.min(fromIndex + pageSize, productListAsList.size()); // chỉ số kết thúc trang tiếp theo
        List<Product> pageContent = productListAsList.subList(fromIndex, toIndex);
                // muốn cho vào PageImpl phải định nghĩa PageContent trước chứ không phải cho tất cả danh sách của content vào
            Page<Product> page = new PageImpl<>(pageContent, Pageable.ofSize(pageSize), totalElements);

        if (keyword != null && !keyword.isEmpty()) {
            page = this.productService.searchProduct(keyword,pageNo);
            model.addAttribute("keyword",keyword);
        }
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        if (pageNo > totalPages) {
            pageNo = totalPages;
        }
        model.addAttribute("listSetProduct",page);
        model.addAttribute("CategoryName",category);
        model.addAttribute("listCategory",listCategory);
        model.addAttribute("totalPage",page.getTotalPages()); //tong so trang
        model.addAttribute("currentPage",pageNo);
        return "PageOfCategory";
    }
}
