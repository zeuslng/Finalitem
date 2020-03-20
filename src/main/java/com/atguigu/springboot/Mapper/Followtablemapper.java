package com.atguigu.springboot.Mapper;

import com.atguigu.springboot.entities.followtable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.INTERNAL;

import java.util.List;

@Mapper
public interface Followtablemapper {

    //校验是否已经关注
    @Select("select * from t_follow where followerId=#{followerId} and followingId=#{followingId}")
    followtable isSubscribe(@Param("followerId")Integer followerId,@Param("followingId")Integer followingId);

    //查询粉丝
    @Select("select followerId from t_follow where followingId = #{id}")
    public List<Integer> getAllfansIdByid(Integer id);

    //查询关注
    @Select("select followingId from t_follow where followerId = #{id}")
    public  List<Integer> getallfollowingbyid(Integer id);

    //添加关注
    @Insert("insert into t_follow (followerId,followingId) values(#{followerId},#{followingId})")
    void Subscribe(@Param("followerId")Integer followerId,@Param("followingId")Integer followingId);
}
