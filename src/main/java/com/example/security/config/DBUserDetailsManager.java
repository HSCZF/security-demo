package com.example.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.security.entity.User;
import com.example.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description:
 *
 * @author CZF
 * @date 2024/10/11 - 19:50
 */
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserMapper userMapper;


    /**
     * 数据库的3个账号明文密码都是：password
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            // 模拟数据库中权限列表，只有拥有这些权限的才能访问得到user/list,user/add
            // 可以尝试注释掉一个，去访问一下
            authorities.add(() -> "USER_LIST");
            authorities.add(() -> "USER_ADD");
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    true,
                    true,
                    true,
                    authorities);  // 3个true代表：用户账号是否过期，用户凭证是否过期，用户是否未被锁定，authorities: 权限列表
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        User insertUser = new User();
        insertUser.setUsername(user.getUsername());
        insertUser.setPassword(user.getPassword());
        insertUser.setEnabled(true);
        userMapper.insert(insertUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
