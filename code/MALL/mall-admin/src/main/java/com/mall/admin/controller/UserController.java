package com.mall.admin.controller;

import com.mall.admin.bean.CommonResult;
import com.mall.admin.bean.User;
import com.mall.admin.service.UserService;
import com.mall.admin.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * Created by macro on 2019/8/21.
 */
@Controller //标注控制层组件
@Api(tags = "UserController",description = " 用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user) throws Exception{
       System.out.println("用户名："+user.getUsername()+"用户密码："+user.getPassword());
        User loginUser = userService.getUser(user);
        if(loginUser!=null && loginUser.getIdCard()!=null) {
            System.out.println("登陆成功");
           redisUtils.remove(loginUser.getUsername());
            return new CommonResult().success("登陆成功！");
        }else{

        }
        System.out.println("登陆失败");
        return new CommonResult().validateFailed("用户名或密码错误！");
    }
}
