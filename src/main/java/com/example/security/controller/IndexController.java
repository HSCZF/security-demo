package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/7 19:28
 */
@RestController
public class IndexController {


    /**
     * 用户认证信息
     *
     * @return null
     */
    @GetMapping("/")
    public Map<String, Object> index() {
        System.out.println("index controller");
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(username);
        System.out.println(principal);
        System.out.println(credentials);
        System.out.println(authorities);

        //创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", username);
        return result;
    }


}
