package com.mall.admin.service;

import com.mall.admin.bean.ShopAddress;

/**
 * 收货地址
 * Created by macro on 2019/8/27.
 */
public interface ShopAddressService {
    int insert(ShopAddress record);

    int update(ShopAddress record);

    int delete( Long id);
}
