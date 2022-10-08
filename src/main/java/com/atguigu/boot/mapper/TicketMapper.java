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
    @Select("select * from ticket where serialNumber=#{serialNumber}")
    Ticket finTicketByNumber(String serialNumber);

    //购买票后修改经济舱开始座位号和座位总数
    @Select("update ticket set economySeat=#{economySeat},ecoBeginNumber=#{ecoBeginNumber} where serialNumber=#{serialNumber}")
    void setEconmyTicket(Integer economySeat,Integer ecoBeginNumber,String serialNumber);

    //购买票后修改商务舱开始座位号和座位总数
    @Select("update ticket set firstSeat=#{firstSeat},firBeginNumber=#{firBeginNumber} where serialNumber=#{serialNumber}")
    void setfirstTicket(Integer firstSeat,Integer firBeginNumber,String serialNumber);
}
