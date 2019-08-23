package com.mall.admin.mapper;

import com.mall.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表
 * Created by macro on 2019/8/22.
 */
@Repository //标注数据访问组件
@Mapper//标注为一个mapper文件
public interface UserMapper {
    //根据用户名和密码查询出用户信息
    User getUser(User user);

    @Select("SELECT * FROM USER")
    List<User> findAll();
}
