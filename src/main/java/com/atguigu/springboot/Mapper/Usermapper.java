package com.atguigu.springboot.Mapper;
import com.atguigu.springboot.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface Usermapper {

    //登录校验
    @Select("select * from t_user where  username = #{username} and password = #{password}")
    User checkUser(@Param("username") String username,@Param("password") String password);

    //根据id查找用户姓名
    @Select("select username from t_user where id = #{userid}")
    String getUsername(Integer userid);

    //根据id查找用户头像地址
    @Select("select profilePicUrl from t_user where id = #{userid}")
    String getprofilePicUrl(Integer userid);

    //增加用户
    @Insert("insert into t_user(username,password) values(#{username},#{password})")
    public void insertUser(User user);

    //删除用户
    @Delete("delect from t_user where id = #{id}")
    public void deleteByid(Integer id);

    //根据用户id修改名字密码
    @Update("update t_user set username=#{username},password=#{password} where id=#{id}")
    public void updateUserByid(Integer id);

    //查询所有用户
    @Select("select * from t_user")
    public List<User> getAlluer();

    //根据id查询用户并显示其所有照片
    @Select("select * from t_user where id = #{id}")
    @Results({
            @Result(property = "photos",column = "id",
            many = @Many(select = "com.atguigu.springboot.Mapper.Photomapper.selectPhotobyUserId"))
    })
    public User getUserByid(Integer id);

    //仅根据id查询用户
    @Select("select * from t_user where id=#{userid}")
    User getUser(Integer userid);

    // 根据粉丝id集合查询粉丝信息
    @Select({
            "<script>",
                    "select * from t_user where id in",
                    "<foreach item='item' index='index' collection='allfansid' open='(' separator=',' close=')'>",
                    "#{item}",
                    "</foreach>",
                    "</script>"
    })
    List<User> getAllfans(@Param("allfansid") List<Integer> allfansid);

    // 根据粉丝id集合查询粉丝信息
    @Select({
            "<script>",
            "select id,profilePicUrl,usernickname from t_user where id in",
            "<foreach item='item' index='index' collection='allfollowingid' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<User> getAllfollowing(@Param("allfollowingid") List<Integer> allfollowingid);

    //根据用户名查找用户
    @Select("select * from t_user where username = #{username}")
    User getUserbyname(String username);
}
