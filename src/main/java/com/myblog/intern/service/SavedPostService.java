package com.myblog.intern.service;

import com.myblog.intern.model.SavedPost;
import com.myblog.intern.repository.SavedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPostService {
    @Autowired
    SavedPostRepository savedPostRepository;

    public List<Integer> getPostId(Integer userid){
        return savedPostRepository.findPostIdsByUserId(userid);
    }

    public boolean savePost(SavedPost savedPost){
        boolean flag;
        try {
            savedPostRepository.save(savedPost);
            flag=true;
        }
        catch (Exception e){
            flag=false;
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean savedPostExists(Integer userId, Integer postId){
        boolean flag=false;
        try {
            if (savedPostRepository.findSavedPostByUserIdAndPostId(userId, postId).getId()!=null){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Service: SavedPostService, Method: savedPostExists, Error: "+e.getMessage());
        }
        return flag;
    }
    public boolean removeSavedPosts(Integer userId, Integer postId){
        boolean flag=false;
        try {
            SavedPost savedPost= savedPostRepository.findSavedPostByUserIdAndPostId(userId, postId);
            savedPostRepository.delete(savedPost);
            if (!savedPostExists(userId, postId)){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Service: SavedPostService, Method: removeSavedPosts, Error: "+e.getMessage());
        }
        return flag;
    }
}
