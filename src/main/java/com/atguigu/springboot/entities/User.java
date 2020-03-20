package com.atguigu.springboot.entities;

import org.apache.catalina.realm.UserDatabaseRealm;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    private Integer id;
    private String username;
    private String password;
    private String profilePicUrl;
    private String wallpaperUrl;
    private String usernickname;

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    private List<photo> photos;

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePic='" + profilePicUrl + '\'' +
                ", wallpaperUrl='" + wallpaperUrl + '\'' +
                ", photos=" + photos +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWallpaperUrl() {
        return wallpaperUrl;
    }

    public void setWallpaperUrl(String wallpaperUrl) {
        this.wallpaperUrl = wallpaperUrl;
    }

    public List<photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<photo> photos) {
        this.photos = photos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
