package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.mypassengers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PassengersMapper {
    @Select("select * from mypassengers")
    List<mypassengers> getPassengers();
}
