package com.hairui.shop.repository;

import com.hairui.shop.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from ec_user where login_name = #{dfff}")
    User login(String loginName);
}
