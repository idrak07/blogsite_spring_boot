package com.myblog.intern.service;

import com.myblog.intern.model.Post;
import com.myblog.intern.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class PostServices {

    @Autowired
    PostRepository postRepository;

    public boolean createPost(Post post){
        boolean flag;
        try {
            postRepository.save(post);
            flag= true;
        }
        catch (Exception e){
            flag=false;
            System.out.println(e.getMessage());
        }
        return flag;


    }
    public Integer getNewPostId(Integer userId, Date date, String details){
        return postRepository.findId(userId,date,details);
    }
    public Post getPostById(Integer id){
        return postRepository.findPostById(id);
    }

}
