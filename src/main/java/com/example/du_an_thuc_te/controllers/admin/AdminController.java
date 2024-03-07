package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.UserService;
import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
@Controller
public class AdminController {
    @Autowired
     private  UserService userService;
    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }
    @RequestMapping("/admin/user")
    public  String user(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Page<User> list = this.userService.getAll(pageNo);
        if (keyword != null && !keyword.isEmpty()) {
            list = this.userService.searchUser(keyword,pageNo);
            model.addAttribute("keyword",keyword);
        }

        model.addAttribute("list", list);
        model.addAttribute("totalPage", list.getTotalPages()); //tong so trang
        model.addAttribute("currentPage", pageNo);
        return "admin/user/index";
    }
//    @RequestMapping("admin/signup")
//    public String signUp(){
//        return "admin/signup";
//    }

}
