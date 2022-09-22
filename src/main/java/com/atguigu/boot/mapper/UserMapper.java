package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//其实就是Dao层,底层写数据库交互语句，增删改查
@Mapper
public interface UserMapper {

    @Select("select * from test2 where name=#{name}")
    User findUser(String name);

    @Select("insert into test2(name,paw) values (#{username},#{userpaw})")
    void addUser(String username, String userpaw);
}