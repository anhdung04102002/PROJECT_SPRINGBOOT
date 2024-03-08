package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.User;
import com.example.du_an_thuc_te.models.userDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//controler gọi đến service chứ không đến repo
public interface UserService {

    User findByUsername(String username);
    List<User> getAll();
    List<User> searchUser(String keyword);
    Page<User> getAll(int pageNo);
    Page<User> searchUser(String keyword,int pageNo);
    void deleteUser(int id);
    void save(userDto userDto);

}
