package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//其实就是Dao层,底层写数据库交互语句，增删改查
@Mapper
public interface UserMapper {

    @Select("select username,userpaw from user where username=#{name}")
    User findUser(String name);

    @Select("insert into user(username,userpaw,personName,personId,sex,phone,email) values (#{username},#{userpaw},#{personName},#{personId},#{sex},#{phone},#{email})")
    void addUser(String username, String userpaw, String personName, String personId, String sex, String phone,String email);
}
