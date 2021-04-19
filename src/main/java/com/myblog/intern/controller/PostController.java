package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.service.SelectedTopicService;
import com.myblog.intern.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            Integer newId=postServices.getNewPostId(postWithTopic.getUserId(),postWithTopic.getDate(), postWithTopic.getDetails());
            for(int i=0;i<postWithTopic.getTopicList().size();i++){
                SelectedTopic selectedTopic=new SelectedTopic( postWithTopic.getTopicList().get(i),newId);
                selectedTopicService.createNewSelectedTopic(selectedTopic);
            }
            return "New post created";
        }
        else{
            return "Sorry! Problem found";
        }
    }

    @RequestMapping(value = "/post/{postId}")
    public PostWithTopic getPost(@PathVariable Integer postId){
        PostWithTopic postWithTopic=null;
      try{
          Post post= postServices.getPostById(postId);
          List<Integer> selectedTopic= selectedTopicService.getSelectedTopicByPostId(postId);
          postWithTopic=new PostWithTopic(post.getId(),post.getUserId(),post.getDate(),post.getDetails(),post.getActive(),post.getImages(),selectedTopic);

      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
        return postWithTopic;
    }



}
