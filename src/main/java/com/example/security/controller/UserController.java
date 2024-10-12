package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/11 - 16:12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    /**
     * swaager : http://localhost:8081/doc.html
     * @param user
     */
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }

}
