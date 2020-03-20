package com.atguigu.springboot.Mapper;
import com.atguigu.springboot.entities.photo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
@Component
public interface Photomapper {

    //插入
    @Insert("insert into photo (name,lujing,url) values (#{name},#{lujing},#{url})")
    public int insertUrl(@Param("name")String name,@Param("lujing")String lujing,@Param("url")String url);

    //查询
    @Select("select * from photo")
    public List<photo> getAllphotos();

    //查询单张照片
    @Select("select * from photo where id=#{id}")
    public photo getOnePhoto(Integer id);

    //根据摄影师id查询他的照片
    @Select("select * from photo where user_id=#{id}")
    public List<photo> getPhotobyUserId(Integer id);

    //修改照片的点赞数   传入多个参数时要用 arg0 arg1 ...进行参数对应 否则会报错
    @Update("update photo set likecount=#{likecount} where id=#{photoid}")
    public void Updatelikecount(@Param("likecount") Integer likecount,@Param("photoid") Integer photoid);

}
