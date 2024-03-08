package com.example.du_an_thuc_te.repositories;

import com.example.du_an_thuc_te.models.Categories;
import com.example.du_an_thuc_te.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select c from User c where  c.fullName like %?1%")
    List<User> searchUser(String keyword);
    User findByUsername(String username);

}
