package com.myblog.intern.service;

import com.myblog.intern.model.Post;
import com.myblog.intern.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {

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
            System.out.println("Service: PostService, Method: createPost, Error:"+e.getMessage());
        }
        return flag;


    }
    public Post getNewPostId(Integer userId, Timestamp date, String title, String details){
        Post post=null;
        try {
            post= postRepository.findPostByUserIdAndDateAndTitleAndDetails(userId, date, title, details);
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: getNewPostId, Error:"+e.getMessage());
        }
        return post;
    }

    public Post getPostById(Integer id){

        Post post=null;
        try {
            post= postRepository.findPostById(id);
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: getPostById, Error: "+e.getMessage());
        }
        return  post;
    }
    public List<Post> getPostsByIds(List<Integer> id){
        List<Post> posts=null;
        try {
            posts=postRepository.findAllById(id);
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: getPostsByIds, Error: "+e.getMessage());
        }
        return posts;
    }

    public List<Post> getRecentPosts(){
        List<Post> posts = null;
        try {
            posts=postRepository.findAllByActiveOrderByDateDesc(1);
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: getRecentPosts, Error: "+e.getMessage());
        }
        return posts;
    }

}
