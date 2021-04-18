package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.repository.SelectedTopicService;
import com.myblog.intern.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostServices postServices;
    @Autowired
    SelectedTopicService selectedTopicService;

    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String createNewPost(@RequestBody PostWithTopic postWithTopic){
        Post post=new Post(postWithTopic.getUserId(),postWithTopic.getDate(),postWithTopic.getDetails(),postWithTopic.getActive(),postWithTopic.getImages());
        if(postServices.createPost(post)){
            Integer newId=postServices.getPostid(postWithTopic.getUserId(),postWithTopic.getDate(), postWithTopic.getDetails());
            for(int i=0;i<postWithTopic.getTopicList().length;i++){
                SelectedTopic selectedTopic=new SelectedTopic( postWithTopic.getTopicList()[i],newId);
                selectedTopicService.createNewSelectedTopic(selectedTopic);
            }
            return "New post created";
        }
        else{
            return "Sorry! Problem found";
        }
    }
}
