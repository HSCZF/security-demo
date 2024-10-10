package com.example.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/7 19:28
 */
@Controller
public class IndexController {

    /**
     * 公开接口
     */
    @GetMapping("/public/hello")
    @ResponseBody
    public String publicHello() {
        return "Hello, Public!";
    }

    /**
     * 私有接口，需要登录后才能访问main主页
     */
    @GetMapping("/private/hello")
    public String privateHello(Model model) {
        return "main";
    }

    /**
     * 登录接口，返回自定义登录页面，<form th:action="@{/login}" method="post">，html使用
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 退出登录接口，返回退出自定义登录页面，<form th:action="@{/logout}" method="post">
     */
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout";  // 返回自定义的 logout.html 页面
    }


}
