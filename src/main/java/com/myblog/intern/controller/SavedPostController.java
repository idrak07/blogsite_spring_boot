package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.SavedPost;
import com.myblog.intern.service.PostService;
import com.myblog.intern.service.SavedPostService;
import com.myblog.intern.service.UserService;
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
    @Autowired
    UserService userService;

    @RequestMapping(value = "/{username}/post/saved", method = RequestMethod.GET)
    public List<Post> getSavedPostByUser(@PathVariable String username){
        Integer userId;
        List<Post> posts=null;
        try{
            userId=userService.getUserIdByUserName(username);
            if (userId!=(-1)){
                posts=postService.getPostsByIds(savedPostService.getPostId(userId));
            }
        }
        catch (Exception e){
            System.out.println("Controller: SavedPostController, Method: getSavedPostByUser, Error: "+e.getMessage());
        }

        return posts;
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
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
