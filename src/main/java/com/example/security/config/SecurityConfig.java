package com.example.security.config;

import com.alibaba.fastjson2.JSON;
import com.example.security.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/7 - 19:35
 */
@Configuration
//@EnableWebSecurity    (在springboot，这个注解可以不要)
public class SecurityConfig {

    /**
     * 密码加密，BCryptPasswordEncoder 实例化
     * 数据库的3个测试用户： admin，Helen，Tom，
     * 不使用这个Bean，需要把3个用户的前缀密码都带加密前缀：{bcrypt}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());  // 禁用 CSRF（跨站请求伪造）防护。
        http.cors(Customizer.withDefaults()); // 跨域
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/list").hasAnyAuthority("USER_LIST")  // 具有USER_LIST权限的用户可以访问/user/list
                        .requestMatchers("/user/add").hasAnyAuthority("USER_ADD")    // 具有USER_ADD权限的用户可以访问/user/add
                        .anyRequest()       // 对所有请求开启授权保护
                        .authenticated()    // 已认证的请求会被自动授权
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error")   // 登录失败的返回地址
                        .successHandler(new MyAuthenticationSuccessHandler())   // 认证成功时的处理
                        .failureHandler(new MyAuthenticationFailureHandler())   // 认证失败时的处理
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                      // 自定义登出URL
                        // .logoutSuccessUrl("/logout-success")    // 登出成功后重定向到登录页面(自定义的logout-success)，和 logoutSuccessHandler 二选一
                        .logoutSuccessHandler(new MyLogoutSuccessHandler())
                        .invalidateHttpSession(true)               // 删除无效化HTTP会话
                        .deleteCookies("JSESSIONID")   // 删除JSESSIONID Cookie
                        .clearAuthentication(true)                 // 清除认证信息
                        .permitAll()
                )
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());  // 请求未认证的接口
                    exception.accessDeniedHandler(new MyAccessDeniedHandler());            // 认证通过，但是权限不足
                })
                .sessionManagement(session -> {  // 会话管理
                    session.maximumSessions(1)   // 设置只有1个会话登录
                            .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
                });
        return http.build();
    }


}
