package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.Mine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MineMapper {
    @Select("insert into mine values (#{buyTicketSerialNumber},#{passengerName},#{passengerId},#{seatNumber},#{seatType},#{holdder},#{ticketNumber},#{departureTime},#{arrivalTime})")
    void addMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, Integer seatNumber, String seatType, String holdder,String ticketNumber,String departureTime,String arrivalTime);

    @Select("select * from mine where buyTicketSerialNumber=#{buyTicketSerialNumber} and passengerName=#{passengerName} and passengerId=#{passengerId}")
    Mine findMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, Integer seatNumber, String seatType, String holdder);

    //根据购票持有人账户查询飞机票
    @Select("select * from mine where holder=#{holder}")
    List<Mine> findMineByHoldder(String holder);

    //删除购买的机票
    @Select("delete from mine where buyTicketSerialNumber=#{buyTicketSerialNumber} and passengerId=#{passengerId}")
    void removeBuy(String buyTicketSerialNumber,String passengerId);
}
