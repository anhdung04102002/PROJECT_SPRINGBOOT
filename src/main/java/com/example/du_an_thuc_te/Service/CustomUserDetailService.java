package com.example.du_an_thuc_te.Service;

import com.example.du_an_thuc_te.models.CustomUserDetail;
import com.example.du_an_thuc_te.models.User;
import com.example.du_an_thuc_te.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("sai thông tin user");
        }
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>(); //DUYỆT CÁC QUYỀN
            Set<UserRole> roles = user.getUserRoles(); // lấy danh sách các quyền
            for(UserRole userRole : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName())); // lấy tên quyền user ví dụ như "ADMIN" hoặc "USER"
            } // SimpleGrantedAuthority trả về tên quyền

        return new CustomUserDetail(grantedAuthorities,user); //danh sách các quyền và user
    }
}
