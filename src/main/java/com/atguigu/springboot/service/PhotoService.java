package com.atguigu.springboot.service;


import com.atguigu.springboot.Mapper.Photomapper;
import com.atguigu.springboot.entities.photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Component
public class PhotoService {

    @Autowired
    private Photomapper photomapper;

    //插入
    public int insertUrl(String name,String lujing,String url){
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=lujing=="+lujing+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=photomapper.insertUrl(name,lujing,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;
    }
    //查询
    public List<photo> getALLphotos(){
        List<photo> photos =photomapper.getAllphotos();
        return photos;
    }

    //查询摄影师的照片
    public List<photo> getmyphotos(Integer id){
        List<photo> photos= photomapper.getPhotobyUserId(id);
        return photos;
    }

    //查询单张
    public photo selectOnePhoto(Integer id){
        photo photo=photomapper.getOnePhoto(id);

        return photo;
    }
}
