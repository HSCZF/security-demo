package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/12 - 14:57
 */
@Controller
public class LoginController {

    /**
     * 登录接口，返回自定义登录页面，<form th:action="@{/login}" method="post">，html使用
     * 一定是@{/login}，普通的/login是无效的
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 退出登录接口，返回退出自定义登录页面，<form th:action="@{/logout}" method="post">
     */
    @GetMapping("/logout-page")
    public String logout() {
        return "logout";  // 返回自定义的 logout.html 页面
    }

}