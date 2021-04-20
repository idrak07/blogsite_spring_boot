package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.SavedPost;
import com.myblog.intern.service.PostServices;
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
    PostServices postServices;

    @RequestMapping(value = "/{userId}/post/saved", method = RequestMethod.GET)
    public List<Post> getSavedPostByUser(@PathVariable Integer userId){
        List<Integer> postIds=savedPostService.getPostId(userId);
        return postServices.getPostsByIds(postIds);
    }

    @RequestMapping(value = "/{userId}/post/save/{postId}", method = RequestMethod.GET)
    public List<Post> setSavedPostByUser(@PathVariable Integer userId){
        List<Integer> postIds=savedPostService.getPostId(userId);
        return postServices.getPostsByIds(postIds);
    }
}
