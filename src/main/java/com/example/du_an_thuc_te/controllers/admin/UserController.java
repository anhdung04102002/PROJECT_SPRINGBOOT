package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.UserService;
import com.example.du_an_thuc_te.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@Service
public class UserController {
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }
    @RequestMapping("/signup")
    public String signUp(){
        return "signup";
    }


}
