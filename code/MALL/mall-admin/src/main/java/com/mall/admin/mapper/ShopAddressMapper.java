package com.mall.admin.mapper;

import com.mall.admin.bean.ShopAddress;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 收货地址信息操作
 * Created by macro on 2019/8/22.
 */
@Repository
@Mapper
public interface ShopAddressMapper {

    int insert(ShopAddress record);

    int update(ShopAddress record);

    int delete(@Param("id") Long id);

    List getAddress(@Param("idCard") String idCard);
}