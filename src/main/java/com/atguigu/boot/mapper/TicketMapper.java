package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Select("select * from ticket where origin=#{origin} and destination=#{destination} and departureDate=#{departureTime}")
    List<Ticket> findTicket(String origin, String destination, String departureTime);

    @Select("select * from ticket where number=#{number}")
    Ticket finTicketByNumber(String number);
//    @Select("select * from ticket where departureDate='2022-9-25'")
//    Ticket findTicket2();
}
