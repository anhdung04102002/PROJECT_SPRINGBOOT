package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.User;

//controler gọi đến service chứ không đến repo
public interface UserService {
    User findByUsername(String username);
}
