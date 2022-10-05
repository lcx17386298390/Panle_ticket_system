package com.atguigu.boot.service;

import com.atguigu.boot.bean.mypassengers;
import com.atguigu.boot.mapper.PassengersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPassengersService {

    @Autowired
    PassengersMapper passengersMapper;

    public List<mypassengers> getPassengers(){
        return passengersMapper.getPassengers();
    }
}
