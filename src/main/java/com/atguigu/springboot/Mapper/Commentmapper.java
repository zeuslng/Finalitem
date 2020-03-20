package com.atguigu.springboot.Mapper;

import com.atguigu.springboot.entities.comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Mapper
public interface Commentmapper {

    @Insert("insert into comment (content,user_id,photo_id,ctime) values(#{content},#{user_id},#{photo_id},#{ctime})")
    void insertcomment(comment comment);

    @Select("select * from comment where photo_id = #{photo_id}")
    List<comment> getallCommentsbuyId(Integer photo_id);

    @Select("select * from comment")
    public  List<comment> getAllComments();

}
