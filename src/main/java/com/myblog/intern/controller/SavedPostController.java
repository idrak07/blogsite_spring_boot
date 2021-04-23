package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.SavedPost;
import com.myblog.intern.service.PostService;
import com.myblog.intern.service.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavedPostController {

    @Autowired
    SavedPostService savedPostService;
    @Autowired
    PostService postService;

    @RequestMapping(value = "/{userId}/post/saved", method = RequestMethod.GET)
    public List<Post> getSavedPostByUser(@PathVariable Integer userId){
        List<Integer> postIds=savedPostService.getPostId(userId);
        return postService.getPostsByIds(postIds);
    }

    @RequestMapping(value = "/{userId}/post/save/{postId}", method = RequestMethod.GET)
    public boolean setSavedPostByUser(@PathVariable Integer userId, @PathVariable Integer postId){
        boolean flag=false;
        try {
            if (!savedPostService.savedPostExists(userId, postId)){
                if(savedPostService.savePost(new SavedPost(null, userId, postId))) {
                    System.out.println("Post saved");
                    flag = true;
                }
            }
        }
        catch (Exception e){
            flag=false;
            System.out.println(e.getMessage());
        }
        return flag;
    }
    @RequestMapping(value = "/{userId}/post/remove-save/{postId}", method = RequestMethod.GET)
    public boolean removeSavedPostByUser(@PathVariable Integer userId,@PathVariable Integer postId){
        boolean flag=false;
        try {
            if (savedPostService.savedPostExists(userId, postId)){
                if(savedPostService.removeSavedPosts(userId, postId)) {
                    System.out.println("Post removed");
                    flag = true;
                }
            }
        }
        catch (Exception e){
            flag=false;
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
