package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

//controler gọi đến service chứ không đến repo
public interface UserService {
    User findByUsername(String username);
    List<User> getAll();
    List<User> searchUser(String keyword);
    Page<User> getAll(int pageNo);
    Page<User> searchUser(String keyword,int pageNo);
    void deleteUser(int id);

}
