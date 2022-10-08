package com.atguigu.boot.service;

import com.atguigu.boot.bean.Ticket;
import com.atguigu.boot.mapper.TicketMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketMapper ticketMapper;

    //根据出发地，目的地，出发时间来查找
    public List<Ticket> findTicket(String origin, String destination, String departureDate) {
        return ticketMapper.findTicket(origin,destination,departureDate);
    }

    //根据航班号查询飞机信息
    public Ticket findTicketByNumber(String serialNumber){
        return ticketMapper.finTicketByNumber(serialNumber);
    }

    //购买票后修改经济舱开始座位号和座位总数
    public void setEconmyTicket(Integer econmySeat,Integer ecoBeginNumber,String serialNumber){
        ticketMapper.setEconmyTicket(econmySeat,ecoBeginNumber,serialNumber);
    }

    //购买票后修改商务舱开始座位号和座位总数
    public void setfirstTicket(Integer firstSeat,Integer firBeginNumber,String serialNumber){
        ticketMapper.setfirstTicket(firstSeat,firBeginNumber,serialNumber);
    }

}
