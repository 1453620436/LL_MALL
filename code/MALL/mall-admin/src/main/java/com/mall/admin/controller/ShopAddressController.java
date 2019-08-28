package com.mall.admin.controller;

import com.mall.admin.bean.CommonResult;
import com.mall.admin.bean.ShopAddress;
import com.mall.admin.service.ShopAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户收货地址管理
 * Created by macro on 2019/8/27.
 */
@Controller
@Api(tags = "ShopAddressController",description = "用户收货地址管理")
@RequestMapping("/shopAddress")
public class ShopAddressController {
    @Autowired
    private ShopAddressService shopAddressService;

    @ApiOperation(value = "加载当前用户的收货地址")
    @RequestMapping(value = "/getAddress" ,method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAddress(@RequestBody String idCarId ) throws Exception{
        return  null;
    }

    @ApiOperation(value = "新增收货地址")
    @RequestMapping(value = "/insertShopAddress" ,method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertShopAddress(@RequestBody ShopAddress shopAddress,String id) throws Exception{
        int count =  shopAddressService.insert(shopAddress);
        if(count>0){
            return new CommonResult().success("收货地址新增成功");
        }
        return new CommonResult().failed("收货地址新增失败");
    }

    @ApiOperation(value = "收货地址修改")
    @RequestMapping(value = "/updateShopAddress" ,method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateShopAddress(@RequestBody ShopAddress shopAddress) throws Exception{
        int count =  shopAddressService.update(shopAddress);
        if(count>0){
            return new CommonResult().success("收货地址修改成功");
        }
        return new CommonResult().failed("收货地址修改失败");
    }

    @ApiOperation(value = "删除收货地址")
    @RequestMapping(value = "/deleteShopAddress",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteShopAddress(@RequestBody Long id) throws  Exception{
        int count =  shopAddressService.delete(id);
        if(count>0){
            return new CommonResult().success("收货地址删除成功");
        }
        return new CommonResult().failed("收货地址删除失败");
    }
}
