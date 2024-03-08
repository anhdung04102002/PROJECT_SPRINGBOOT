package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.*;
import com.example.du_an_thuc_te.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    //    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> searchUser(String keyword) {
        return this.userRepository.searchUser(keyword);
    }

    @Override
    public Page<User> getAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUser(String keyword, int pageNo) {
        List<User> list = this.searchUser(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1, 10);
        int totalItems = list.size();
        if (totalItems == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            int start = (int) pageable.getOffset();
            int end = (int) Math.min((start + pageable.getPageSize()), totalItems);
            List<User> sublist = list.subList(start, end);
            return new PageImpl<>(sublist, pageable, totalItems);
        }
    }

    @Override
    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void save(userDto userDto) {
        Role role = new Role();
        role.setName("USER");

        UserRole userRole = new UserRole();
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), true, userDto.getGender(), userDto.getAddress(), userDto.getEmail(), userDto.getTelephone(), userDto.getFullname());
        user.setUserRoles(userRoles);

        this.userRepository.save(user);
    }

}
