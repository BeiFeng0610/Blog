package com.beifeng.service.impl;

import com.beifeng.dao.UserMapper;
import com.beifeng.domain.User;
import com.beifeng.service.UserService;
import com.beifeng.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 19:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {

        User user = userMapper.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
