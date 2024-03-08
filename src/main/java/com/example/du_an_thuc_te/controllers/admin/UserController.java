package com.example.du_an_thuc_te.controllers.admin;

import com.example.du_an_thuc_te.Service.UserService;
import com.example.du_an_thuc_te.models.User;
import com.example.du_an_thuc_te.models.userDto;
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
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }
    @RequestMapping("/signup")
    public String signUp(Model model){
        userDto user = new userDto();
        model.addAttribute("user",user);
        return "signup";
    }
    @PostMapping("/signup/dk")
    public String saveUser(@ModelAttribute("user") userDto userDto) {
        userService.save(userDto);
//        model.addAttribute("message","Register Successfully");
        return "redirect:/signup";
    }

}
