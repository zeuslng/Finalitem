package com.atguigu.springboot.controller;


import com.atguigu.springboot.Mapper.Photomapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.User;
import com.atguigu.springboot.entities.photo;
import com.atguigu.springboot.service.FollowtableService;
import com.atguigu.springboot.service.PhotoService;
import com.atguigu.springboot.service.UserService;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

@Controller
public class logincontroller {

    @Autowired
    private Photomapper photomapper;
    @Autowired
    private Usermapper usermapper;
        @PostMapping(value = "/user/login")
        public String login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Map<String,Object> maps,
                           HttpSession session,
                           Model model){
            User user =usermapper.checkUser(username,password);
            if(user==null){
                //登录失败
                maps.put("message","用户名或者密码错误");
                return "index";
            }else if (user!=null){
                List<photo> photos=photomapper.getAllphotos();//@Autowrite注解别忘记了！！！！！！！！！！
                //model.addAttribute("user",user);
                model.addAttribute("photos",photos);
                //session.setAttribute("loginUser",username);
                session.setAttribute("LoginUser",user);
               // session.setAttribute("photos",photos);
                //登录成功 防止表单重复提交，可以重定向到主页
                return "redirect:/community";
            }else{
                //登录失败
                maps.put("message","登录失败");
                return "index";
            }

        }

        //退出登录
        @RequestMapping(value="/tologout")
        public String Logout(HttpSession session){
            session.invalidate();
            return "index";
        }

}
