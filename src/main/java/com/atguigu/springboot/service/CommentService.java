package com.atguigu.springboot.service;

import com.atguigu.springboot.Mapper.Commentmapper;
import com.atguigu.springboot.entities.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private Commentmapper commentmapper;

    public void addComment(comment comment){

        commentmapper.insertcomment(comment);

    }

}
