package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entity.User;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/11 - 16:10
 */
public interface UserService extends IService<User> {


    void saveUserDetails(User user);
}
