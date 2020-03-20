package com.atguigu.springboot.controller;


import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.Mapper.Followtablemapper;
import com.atguigu.springboot.entities.User;
import com.atguigu.springboot.entities.photo;
import com.atguigu.springboot.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    Usermapper usermapper;
    @Autowired
    Followtablemapper followtablemapper;
    @Autowired
    PhotoService photoService;

    @GetMapping("/getuser/{id}")
    public User getuser(@PathVariable("id") Integer id){
        return usermapper.getUserByid(id);
    }

    @GetMapping("/adduser")
    public User adduser(User user){
        usermapper.insertUser(user);
        return user;
    }



}
