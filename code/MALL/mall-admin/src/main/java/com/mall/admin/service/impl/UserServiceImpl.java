package com.mall.admin.service.impl;

import com.mall.admin.bean.User;
import com.mall.admin.mapper.UserMapper;
import com.mall.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户实现类
 * Created by macro on 2019/8/22.
 */
@Service //标注业务层组件
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
