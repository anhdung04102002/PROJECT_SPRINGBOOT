package com.example.du_an_thuc_te;

import com.example.du_an_thuc_te.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
// 2 cái bắt buộc trong file config của spring security
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/*").permitAll()
                        .requestMatchers("/admin/**")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
                        .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin", true));
        return http.build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return ((web) -> web.ignoring().requestMatchers("/assets/**"));
    } // cho phép truy cập vào css và js ... template
}
