package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/7 - 19:35
 */
@Configuration
//@EnableWebSecurity    (在springboot，这个注解可以不要)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                      // 自定义登出URL
                        .logoutSuccessUrl("/logout-success")    // 登出成功后重定向到登录页面
                        .invalidateHttpSession(true)               // 删除无效化HTTP会话
                        .deleteCookies("JSESSIONID")   // 删除JSESSIONID Cookie
                        .clearAuthentication(true)                 // 清除认证信息
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")     // 填写{noop}表示不加密，测试使用，实际使用需要加密
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
