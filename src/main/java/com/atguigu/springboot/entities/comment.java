package com.atguigu.springboot.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class comment {
    private Integer cid;
    private String content;
    private int user_id;//评论者的id
    private int photo_id;//所属照片的id
    private String userhead;//评论者的头像地址
    private String usernickname;//评论者昵称
    private String ctime;

    public String getUserhead() {
        return userhead;
    }

    public void setUserhead(String userhead) {
        this.userhead = userhead;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }



    public Integer getId() {
        return cid;
    }

    public void setId(Integer id) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
