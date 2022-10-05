package com.atguigu.boot.service;

import com.atguigu.boot.bean.Mine;
import com.atguigu.boot.mapper.MineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MineService {

    @Autowired
    MineMapper mineMapper;

    //添加购买机票
    public String addMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, String seatNumber, String seatType, String holdder) {
        //先判断是否已经购买了，或者航班号不存在
        Mine mine = mineMapper.findMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder);
        //自己的数据库中查询为空则可以买
        if (mine == null) {
            mineMapper.addMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder);
            return "ok";
        }
        //否则错误
        return "error";
    }
}
