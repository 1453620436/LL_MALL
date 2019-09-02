package com.mall.admin.service;

import com.mall.admin.bean.ShopAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址
 * Created by macro on 2019/8/27.
 */
public interface ShopAddressService {
    int insert(ShopAddress record);

    int update(ShopAddress record);

    int delete( Long id);

    List getAddress(String idCard);
}
