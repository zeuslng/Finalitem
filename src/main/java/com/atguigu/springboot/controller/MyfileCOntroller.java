package com.atguigu.springboot.controller;

import com.atguigu.springboot.Mapper.Commentmapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.User;
import com.atguigu.springboot.entities.comment;
import com.atguigu.springboot.service.FollowtableService;
import com.atguigu.springboot.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.atguigu.springboot.entities.photo;

//@RequestMapping("/file")
@Controller
public class MyfileCOntroller {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private FollowtableService followtableService;
    @Autowired
    private Commentmapper commentmapper;
    @Autowired
    private Usermapper usermapper;

    private String  url;

    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {

        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }


        // 获取文件名
        String fileName = file.getOriginalFilename();

//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");

        //加个时间戳，尽量避免文件名称重复
        String path = "E:/fileUpload/" +fileName;

        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            url="http://localhost:8080/images/"+fileName;
            int jieguo= photoService.insertUrl(fileName,path,url);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            return "上传失败";
        }

        return "上传成功,文件url=="+url+"";
    }

    //显示当前用户的所有图片
    @RequestMapping("/like")
    public String showAll(Model model){
//        List<photo> photos=photoService.getALLphotos();
//        model.addAttribute("photos",photos);
        return "/vedioweb/like";
    }

    //社区(所有用户的图片)
    @RequestMapping("/community")
    public String community(Model model){
        List<photo> photos=photoService.getALLphotos();
        model.addAttribute("photos",photos);
        return "/community";
    }

    //当前用户的主页
    @RequestMapping("/myhost")
    public String myhost(Model model){
        List<photo> photos=photoService.getALLphotos();
        model.addAttribute("photos",photos);
        return "/vedioweb/myhost2";
    }

    @GetMapping("/showonephoto{id}")
    public String picDisplay(@PathVariable("id") Integer id, Model model){
        List<comment> comments=commentmapper.getallCommentsbuyId(id);
        photo photo= photoService.selectOnePhoto(id);
        model.addAttribute("comments",comments);
        model.addAttribute("photo",photo);
        return "/vedioweb/showonephoto";
    }

    //Getmapping带参数跳转页面 跳转路径和return的路径名字要一致 ：都是跳转页面的名字！！！！！！！！！！！！！！！！！！！！！！！！！
    @GetMapping("/buy{id}")
    public String tobuyphoto(@PathVariable("id") Integer id,Model model){
        photo photo= photoService.selectOnePhoto(id);
        model.addAttribute("thisPhoto",photo);
        return "/buy";
    }

    //Getmapping带参数跳转页面 跳转路径和return的路径名字要一致 ：都是跳转页面的名字！！！！！！！！！！！！！！！！！！！！！！！！！
    @GetMapping("/myhost{user_id}")
    public String toUserHost(@PathVariable("user_id") Integer user_id,Model model){
        User author = usermapper.getUser(user_id);
        List<photo> photos=photoService.getmyphotos(user_id);
        model.addAttribute("photos",photos);
        model.addAttribute("author",author);
        return "/myhost";
    }

}
