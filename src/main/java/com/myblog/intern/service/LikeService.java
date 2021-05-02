package com.myblog.intern.service;


import com.myblog.intern.model.Like;
import com.myblog.intern.model.Post;
import com.myblog.intern.repository.LikeRepository;
import com.myblog.intern.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PostRepository postRepository;

    public boolean likeExists(Integer userId, Integer postId){
        boolean flag=false;
        try{
            flag=likeRepository.existsLikeByUserIdAndAndPostId(userId, postId);
        }
        catch (Exception e){
            System.out.println("Service: LikeService, Method: likeExists, Error: "+e.getMessage());
        }
        return flag;
    }

    public boolean addLike(Like like){
        boolean flag=false;
        try{
            likeRepository.save(like);
            Post post=postRepository.getOne(like.getPostId());
            post.setLikes(post.getLikes()+1);
            postRepository.save(post);
            flag=true;
        }
        catch (Exception e){
            System.out.println("Service: LikeService, Method: addLike, Error: "+e.getMessage());
        }
        return flag;
    }
    public void removeLike(Like like){
        try{
            likeRepository.deleteByUserIdAAndPostId(like.getUserId(), like.getPostId());
            Post post=postRepository.getOne(like.getPostId());
            post.setLikes(post.getLikes()-1);
            postRepository.save(post);
        }
        catch (Exception e){
            System.out.println("Service: LikeService, Method: removeLike, Error: "+e.getMessage());
        }
    }
}
