package com.mall.admin.service;

import com.mall.admin.bean.User;

import java.util.List;

/**
 * 用户
 * Created by macro on 2019/8/22.
 */
public interface UserService {
    User getUser(User user);

    int inserUser(User user);

    List<User> findUser(User user);

    List<User> findAll();
}
