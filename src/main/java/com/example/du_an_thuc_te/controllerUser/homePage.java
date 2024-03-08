package com.example.du_an_thuc_te.controllerUser;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Service
public class homePage {
    @RequestMapping("/index")
    public  String index(){
        return "index";
    }
}
