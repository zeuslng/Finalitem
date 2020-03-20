package com.atguigu.springboot.service;

import com.atguigu.springboot.Mapper.Followtablemapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserService {

    @Autowired
    private Usermapper usermapper;
    @Autowired
    private Followtablemapper followtablemapper;

    //根据用户名获取用户
    public User getUserbyname(String username){
        return usermapper.getUserbyname(username);
    }

}
