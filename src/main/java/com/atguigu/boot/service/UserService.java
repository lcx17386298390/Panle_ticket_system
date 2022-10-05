package com.atguigu.boot.service;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    //查询人员逻辑
    public Integer findUser(String name, String paw) {
        try {
            User user = userMapper.findUser(name);
            if (name.equals(user.getName()) && paw.equals(user.getPaw())) {
                //账号密码匹配成功
                return 200;
            } else {
                return 404;
            }
        } catch (Exception e) {
            return 404;
        }
    }

    public void addUser(String username, String userpaw, String personName, String personId, String sex, String phone, String email) {
        userMapper.addUser(username, userpaw, personName, personId, sex, phone, email);
    }
}
