package com.atguigu.springboot.controller;

import com.atguigu.springboot.entities.comment;
import com.atguigu.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    //添加评论
    @RequestMapping(value = "/addmycomment",method = RequestMethod.POST)
    public void addCom(String content,Integer userId,Integer photoId,Date ctime){
        System.out.println("=======================");
        System.out.println(content+"+"+userId+"+"+photoId+"+"+ctime);
        //commentService.addComment(content,userId,photoId,ctime);
       // return "vedioweb/comment";
    }

}
