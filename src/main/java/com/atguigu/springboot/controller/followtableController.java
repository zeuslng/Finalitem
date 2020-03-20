package com.atguigu.springboot.controller;

import com.atguigu.springboot.Mapper.Followtablemapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.User;
import com.atguigu.springboot.service.FollowtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class followtableController {

    @Autowired
    Followtablemapper followtablemapper;
    @Autowired
    FollowtableService followtableService;
    @Autowired
    Usermapper usermapper;

    //获取所有粉丝的id
    @GetMapping("/getfansid/{id}")
    public List<Integer> getallfansid(@PathVariable("id") Integer id){
        System.out.println(followtableService.getfansidByid(id));
        return followtableService.getfansidByid(id);
    }

    //获取所有关注的id
    @GetMapping("/getfollowingsid/{id}")
    @ResponseBody
    public List<Integer> getallfollowingsid(@PathVariable("id") Integer id){
        List<Integer> ids = followtableService.getfollowingsidByid(id);
        System.out.println(ids);
        return ids;
    }

    //显示关注列表
    @GetMapping("/showmyfans{id}")
    public String showmyfollowing(@PathVariable("id")Integer id, Model model, Map<String,String> map){
        try {
            List<User> allfollowing=followtableService.getfollowingInfo(id);
        model.addAttribute("allfans",allfollowing);
    }catch (Exception e){
        e.printStackTrace();
        map.put("msg","您还没有任何关注！");
    }
       User thisauthor=usermapper.getUser(id);
       model.addAttribute("thisauthor",thisauthor);

       return "vedioweb/myfans";
    }

}
