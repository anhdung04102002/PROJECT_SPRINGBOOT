package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
