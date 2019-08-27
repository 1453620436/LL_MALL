package com.mall.admin.controller;

import com.mall.admin.bean.CommonResult;
import com.mall.admin.bean.User;
import com.mall.admin.service.UserService;
import com.mall.admin.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody User user) throws Exception{
        System.out.println("注册用户信息："+user.getUsername()+","+user.getIdCard());
        try {
        List listUser = new ArrayList();
        //注册前先校验证件号是否存在一注册的信息，已经注册的证件号不能再次注册，除非进行解绑，需要通过联系客服
        User validateIdCard = new User();
        validateIdCard.setIdCard(user.getIdCard());
        listUser = userService.findUser(validateIdCard);
        if(null != listUser && listUser.size()>0){
            return new CommonResult().failed("已经注册的证件号不能再次注册，可联系客服进行注销！");
        }
        //新证件号注册，用户名不能重复
        User validateUsername = new User();
        validateUsername.setUsername(user.getUsername());
        listUser = userService.findUser(validateUsername);
        if(null != listUser && listUser.size()>0){
            return new CommonResult().failed(user.getUsername()+"用户名已存在！");
        }
        //手机号不能重复
        User validateTelethone = new User();
        validateTelethone.setTelethone(user.getTelethone());
        listUser = userService.findUser(validateTelethone);
        if(null != listUser && listUser.size()>0) {
            User userFind = (User) listUser.get(0);
            return new CommonResult().failed(user.getTelethone() + "手机号已经注册，用户名为：" + userFind.getUsername());
        }
        //邮箱不能重复
        User validateEmail = new User();
        validateEmail.setEmail(user.getEmail());
        listUser = userService.findUser(validateEmail);
        if(null != listUser && listUser.size()>0) {
            User userFind = (User) listUser.get(0);
            return new CommonResult().failed(user.getEmail() + "邮箱已经注册，用户名为：" + userFind.getUsername());
        }
        //注册新用户
        int count = userService.inserUser(user);
        if(count>0){
            System.out.println("注册成功");
            return new CommonResult().success("注册成功！");
        }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("注册失败");
            return new CommonResult().failed("注册失败！");
        }
        System.out.println("注册失败");
        return new CommonResult().failed("注册失败！");
    }
}
