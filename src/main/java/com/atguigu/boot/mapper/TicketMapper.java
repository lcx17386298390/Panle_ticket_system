package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Select("select * from ticket where origin=#{origin} and destination=#{destination} and departureDate=#{departureTime}")
    List<Ticket> findTicket(String origin, String destination, String departureTime);

    //购买时根据序列号来查找飞机票情况
    @Select("select * from ticket where number=#{serialNumber}")
    Ticket finTicketByNumber(String serialNumber);
}
