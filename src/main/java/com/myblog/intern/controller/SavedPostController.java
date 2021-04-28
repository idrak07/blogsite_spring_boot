package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.model.SavedPost;
import com.myblog.intern.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SavedPostController {

    @Autowired
    SavedPostService savedPostService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    SelectedTopicService selectedTopicService;
    @Autowired
    PostWithTopicService postWithTopicService;
    @Autowired
    JwtService jwtService;

    @RequestMapping(value = "/post/saved", method = RequestMethod.GET)
    public List<PostWithTopic> getSavedPostByUser(HttpServletRequest request){
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userId;
        List<Post> posts;
        List<PostWithTopic> postWithTopics=new ArrayList<>();
        try{
            userId=userService.getUserIdByUserName(username);
            if (userId!=(-1)){
                posts=postService.getPostsByIds(savedPostService.getPostId(userId));
                postWithTopics=postWithTopicService.getPostsWithTopic(posts);
            }
        }
        catch (Exception e){
            System.out.println("Controller: SavedPostController, Method: getSavedPostByUser, Error: "+e.getMessage());
        }

        return postWithTopics;
    }

    @RequestMapping(value = "/post/save/{postId}", method = RequestMethod.GET)
    public boolean setSavedPostByUser(HttpServletRequest request, @PathVariable Integer postId){
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        boolean flag=false;
        Integer userId;
        try {
            userId=userService.getUserIdByUserName(username);
            if (userId!=(-1)){
                if (!savedPostService.savedPostExists(userId, postId)){
                    if(savedPostService.savePost(new SavedPost(null, userId, postId))) {
                        System.out.println("Post saved");
                        flag = true;
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }
    @RequestMapping(value = "/post/remove-save/{postId}", method = RequestMethod.GET)
    public boolean removeSavedPostByUser(HttpServletRequest request,@PathVariable Integer postId){
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        boolean flag=false;
        Integer userId;
        try {
            userId=userService.getUserIdByUserName(username);
            if (userId!=(-1)){
                if (savedPostService.savedPostExists(userId, postId)){
                    if(savedPostService.removeSavedPosts(userId, postId)) {
                        System.out.println("Post removed");
                        flag = true;
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
