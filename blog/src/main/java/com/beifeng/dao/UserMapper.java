package com.beifeng.dao;

import com.beifeng.domain.User;

public interface UserMapper {

    User findByUsernameAndPassword(String username, String password);

}