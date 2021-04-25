package com.myblog.intern.service;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostHistory;
import com.myblog.intern.repository.PostHistoryRepository;
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
    @Autowired
    PostHistoryRepository postHistoryRepository;

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
            post.setView(post.getView()+1);
            postRepository.save(post);
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

    public List<Post> mostViewedPost(){
        List<Post> posts = null;
        try {
            posts=postRepository.findAllByActiveOrderByViewDesc(1);
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: mostViewedPost, Error: "+e.getMessage());
        }
        return posts;
    }

    public boolean postExists(Integer postId){
        boolean flag= false;
        try{
            if (postRepository.existsPostByIdAndActive(postId,1)){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: postExists, Error: "+e.getMessage());
        }
        return flag;
    }

    public boolean updatePost(Integer id, String title,String shortDescription, String details, Timestamp updateTime){
        boolean flag=false;
        Post post= postRepository.getOne(id);
        try{
            postHistoryRepository.save(new PostHistory(null,post.getId(),post.getTitle(),post.getShortDescription(),post.getDetails(),post.getUpdatedAt()));
            post.setTitle(title);
            post.setShortDescription(shortDescription);
            post.setDetails(details);
            post.setUpdatedAt(updateTime);
            postRepository.save(post);
            flag=true;
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: updatePost, Error: "+e.getMessage());
        }
        return flag;
    }


    public boolean deletePost(Integer postId, Timestamp updateTime) {
        boolean flag=false;
        Post post= postRepository.getOne(postId);
        try{
            post.setActive(0);
            post.setUpdatedAt(updateTime);
            postRepository.save(post);
            flag=true;
        }
        catch (Exception e){
            System.out.println("Service: PostService, Method: deletePost, Error: "+e.getMessage());
        }
        return flag;
    }


}
