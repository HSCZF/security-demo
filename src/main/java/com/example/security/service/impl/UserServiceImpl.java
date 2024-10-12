package com.example.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.config.DBUserDetailsManager;
import com.example.security.entity.User;
import com.example.security.mapper.UserMapper;
import com.example.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/11 - 16:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUserDetails(User user) {
        // 使用 BCryptPasswordEncoder 来加密密码，在SecurityConfig中实例化 BCryptPasswordEncoder
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(encodedPassword)
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
