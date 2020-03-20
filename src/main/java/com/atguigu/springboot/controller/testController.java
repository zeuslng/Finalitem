package com.atguigu.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import com.atguigu.springboot.Mapper.Commentmapper;
import com.atguigu.springboot.Mapper.Followtablemapper;
import com.atguigu.springboot.Mapper.Photomapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.comment;
import com.atguigu.springboot.entities.photo;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.service.FollowtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class testController {

    @Autowired
    CommentService commentService;
    @Autowired
    Commentmapper commentmapper;
    @Autowired
    Usermapper usermapper;
    @Autowired
    Photomapper photomapper;
    @Autowired
    Followtablemapper followtablemapper;
    @Autowired
    FollowtableService followtableService;

    @RequestMapping(value = "/addcomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public comment addcomment(HttpServletRequest request) throws ParseException {
        // 获得key为username的value
        String content = request.getParameter("mycomment");
        String user_Id = request.getParameter("userId");
        String photo_Id = request.getParameter("photoId");

        String userhead=usermapper.getprofilePicUrl(Integer.valueOf(user_Id));
        String usernickname=usermapper.getUsername(Integer.valueOf(user_Id));

        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        sd.format(date);
        comment comment=new comment();
        comment.setUser_id(Integer.valueOf(user_Id));
        comment.setPhoto_id(Integer.valueOf(photo_Id));
        comment.setContent(content);
        comment.setCtime(sd.format(date));
        comment.setUserhead(userhead);
        comment.setUsernickname(usernickname);

        System.out.println("添加参数成功=================================");
        commentmapper.insertcomment(comment);
        System.out.println("添加评论成功=================================");

        //直接返回当前评论
        return comment;
    }

    @RequestMapping(value = "/addLikecount" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean addLikecount(HttpServletRequest request){
        int likecout=Integer.parseInt(request.getParameter("likecount"));
        int photoId=Integer.parseInt(request.getParameter("photoId"));
        likecout+=1;
        System.out.println(likecout+"     "+photoId);
        photomapper.Updatelikecount(likecout,photoId);
        return true;
    }

    @RequestMapping(value = "/getallComment",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<comment> getallComment(){
        List<comment> comments=commentmapper.getAllComments();
        return comments;
    }

    @RequestMapping(value = "/doSubscirbe",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean doSubscribe(HttpServletRequest request){
        String thisUser=request.getParameter("thisUser");
        String currentUser=request.getParameter("currentUser");
        boolean isSubscrib=followtableService.isFollowing(Integer.valueOf(currentUser),Integer.valueOf(thisUser));
        if (!isSubscrib){
            followtablemapper.Subscribe(Integer.valueOf(currentUser),Integer.valueOf(thisUser));
            return true;
        }
            return false;
    }

}