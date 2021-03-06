package com.beifeng.dao;

import com.beifeng.domain.User;

public interface UserMapper {

    User findByUsernameAndPassword(String username, String password);

    Integer updateUser(User user);

    void updateAdminComment(User user);

    void updateAdminMessage(User user);
}