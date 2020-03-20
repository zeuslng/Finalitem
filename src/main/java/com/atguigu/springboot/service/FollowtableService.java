package com.atguigu.springboot.service;

import com.atguigu.springboot.Mapper.Followtablemapper;
import com.atguigu.springboot.Mapper.Usermapper;
import com.atguigu.springboot.entities.User;
import com.atguigu.springboot.entities.followtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class FollowtableService {

    @Autowired
    private Followtablemapper followtablemapper;
    @Autowired
    private Usermapper usermapper;

    //判断是否已经关注
    public boolean isFollowing(Integer followerid,Integer followingId){
        followtable ft=followtablemapper.isSubscribe(followerid,followingId);
        if(ft==null){
            return false;
        }
        return true;
    }

    //获取所有粉丝的id
    public List<Integer> getfansidByid(Integer id){
        List<Integer> fansid=followtablemapper.getAllfansIdByid(id);
        return fansid;
    }

    //显示所有粉丝信息
    public List<User> getfansinfo(Integer id){
        List<Integer> allfansId=followtablemapper.getAllfansIdByid(id);
        System.out.println(allfansId);
        List<User> allfans=usermapper.getAllfans(allfansId);
        return  allfans;
    }

    //显示所有关注者的信息
    public List<User> getfollowingInfo(Integer id){
        List<Integer> allFollowingId = followtablemapper.getallfollowingbyid(id);
        List<User> allFollowing =usermapper.getAllfollowing(allFollowingId);
        return allFollowing;
    }

    //获得所有关注的id
    public List<Integer> getfollowingsidByid(Integer id){
        List<Integer> fansid=followtablemapper.getallfollowingbyid(id);
        return fansid;
    }
}
