package com.mall.admin.controller;

import com.mall.admin.bean.CommonResult;
import com.mall.admin.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 */
@Controller
@Api(tags = "UserController",description = " 用户管理")
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user){
        return new CommonResult().success("登录成功！");
    }
}
