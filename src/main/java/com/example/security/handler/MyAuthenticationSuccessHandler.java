package com.example.security.handler;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * Description:
 * 认证成功的响应
 *
 * @author CZF
 * @date 2024/10/12 - 15:09
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户身份信息
        Object principal = authentication.getPrincipal();
        // 创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "登录成功");
        result.put("data", principal);

        // 转换成json字符串
        String json = JSON.toJSONString(result);

        // 返回响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

    }
}
