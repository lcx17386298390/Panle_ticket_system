package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.Mypassengers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PassengersMapper {

    @Select("select * from mypassengers where hodler=#{hodler}")
    List<Mypassengers> getPassengers(String hodler);

    //根据身份证号查人
    @Select("select * from mypassengers where passengerId=#{passengerId}")
    Mypassengers getPassengerById(String passengerId);

    @Select("delete from mypassengers where passengerId=#{passengerId}")
    void deletePassenger(String passengerId);
}
