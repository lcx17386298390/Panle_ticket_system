package com.atguigu.boot.service;

import com.atguigu.boot.bean.Mypassengers;
import com.atguigu.boot.mapper.PassengersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPassengersService {

    @Autowired
    PassengersMapper passengersMapper;

    //返回所有人
    public List<Mypassengers> getPassengers(String hodler){
        return passengersMapper.getPassengers(hodler);
    }

    //根据身份证号查人，包装返回
    public Mypassengers getPassengerById(String passengerId){
        return passengersMapper.getPassengerById(passengerId);
    }

    public void deletePassenger(String passengerId){
        passengersMapper.deletePassenger(passengerId);
    }
}
