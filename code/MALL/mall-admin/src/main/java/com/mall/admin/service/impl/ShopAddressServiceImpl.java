package com.mall.admin.service.impl;

import com.mall.admin.bean.ShopAddress;
import com.mall.admin.mapper.ShopAddressMapper;
import com.mall.admin.service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址实现类
 * Created by macro on 2019/8/27.
 */
@Service
public class ShopAddressServiceImpl implements ShopAddressService {

    @Autowired
    private ShopAddressMapper shopAddressMapper;

    @Override
    public int insert(ShopAddress record) {
        return shopAddressMapper.insert(record);
    }

    @Override
    public int update(ShopAddress record) {
        return shopAddressMapper.update(record);
    }

    @Override
    public int delete( Long id) {
        return shopAddressMapper.delete(id);
    }

    @Override
    public List getAddress(String idCard) {
        return shopAddressMapper.getAddress(idCard);
    }
}
