package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.Mine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MineMapper {
    @Select("insert into mine(*) values (#{buyTicketSerialNumber},#{passengerName},#{passengerId},#{seatNumber},#{seatType},#{holdder})")
    void addMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, String seatNumber, String seatType, String holdder);

    @Select("select * from mine where number=#{number}")
    Mine findMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, String seatNumber, String seatType, String holdder);
}
