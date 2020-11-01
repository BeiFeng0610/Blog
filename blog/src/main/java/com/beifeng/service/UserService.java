package com.beifeng.service;

import com.beifeng.domain.User;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 18:57
 */
public interface UserService {

    User checkUser(String username, String password);

}
