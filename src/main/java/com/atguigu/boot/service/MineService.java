package com.atguigu.boot.service;

import com.atguigu.boot.bean.Mine;
import com.atguigu.boot.mapper.MineMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MineService {

    @Autowired
    MineMapper mineMapper;

    //添加购买机票
    public String addMineBuy(String buyTicketSerialNumber, String passengerName, String passengerId, Integer seatNumber, String seatType, String holdder,String ticketNumber,String departureTime,String arrivalTime) {
        //先判断是否已经购买了，或者航班号不存在
        Mine mine = mineMapper.findMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder);
        //自己的数据库中查询为空则可以买
        if (mine == null) {
            mineMapper.addMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder,ticketNumber,departureTime,arrivalTime);
            Mine mine1 = mineMapper.findMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder);
            if(mine1.getSeatType().equals("economySeat")){
                return "顾客"+passengerName+"的座位是经济舱"+mine1.getSeatNumber()+"号";
            }else{
                return "顾客"+passengerName+"的座位是头等舱"+mine1.getSeatNumber()+"号";
            }
        }
        //否则错误
        return "error";
    }
    //根据购票持有人账户查询飞机票
    public List<Mine> findMineByHoldder(String holder){
        return mineMapper.findMineByHoldder(holder);
    }

    //删除购买的机票
    public void removeBuy(String buyTicketSerialNumber,String passengerId){
        mineMapper.removeBuy(buyTicketSerialNumber,passengerId);
    }
}
