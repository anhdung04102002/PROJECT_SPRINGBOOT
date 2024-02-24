package com.example.du_an_thuc_te;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testPass {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
