package com.example.du_an_thuc_te;

import com.example.du_an_thuc_te.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/login","/signup/**").permitAll() //phải cho cấp quyền signup/** thì mới đăng ký đưuọc không th phải login đến signup thì mới đăng ký dđược nên ta cấp quyền cho signup luôn
                        .requestMatchers("/admin/**")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/index", false))
//                        .usernameParameter("username").passwordParameter("password"))
                .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/login"))
        ;

        return http.build();
    }
    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return ((web) -> web.ignoring().requestMatchers("/assets/**").requestMatchers("/fe/**").requestMatchers("uploads/**"));
    } // cho phép truy cập vào css và js ... template

}
