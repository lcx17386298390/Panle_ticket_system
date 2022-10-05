package com.atguigu.boot.service;

import com.atguigu.boot.bean.Ticket;
import com.atguigu.boot.mapper.TicketMapper;
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
//    public Ticket findTicket(/*String origin, String destination, String departureTime*/) {
////        return ticketMapper.findTicket(origin, destination, departureTime);
//        return ticketMapper.findTicket2();
//    }
}
