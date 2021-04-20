package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.service.SelectedTopicService;
import com.myblog.intern.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostServices postServices;
    @Autowired
    SelectedTopicService selectedTopicService;


    /*This method provide new post create option for user
    * Url: localhost:8080/post/create
    * Data :
    * At first check you have a user with user id with value 1 in user table
    * Check you have topic in topic table
    * {"description": "Technology"}, {"description": "Agriculture"}
    * {"userId":1,"date":"2021-04-19","details":"+wmnembjn+j+keb+e+%0D%0Alnrel%0D%0A%0D%0Aljetnlnerl+noiner.%0D%0A","active":1,"images":",nsdvf","topicList": [1]}
    * */
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
    /*This method is used to get specific post
    * Url: localhost:8080/post/12
    * Data :Change value 12 for different result as it is post id
    * */
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

    /*This method is used for removing selected topic from a post
    * Url: localhost:8080/post/13/remove/topic/5
    * Change value 13 for postId and 5 for topicId
    * */
    @RequestMapping(value = "/post/{postId}/remove/topic/{topicId}")
    public boolean removeSelectedTopic(@PathVariable Integer postId,@PathVariable Integer topicId){
        boolean result=false;
        try{
            if (selectedTopicService.delSelectedTopic(postId,topicId)){
                result=true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  result;
    }

    /*This method is used for removing selected topic from a post
    * Url: localhost:8080/post/13/remove/topic/5
    * Change value 13 for postId and 5 for topicId
    * */
    @RequestMapping(value = "/post/add/topic", method = RequestMethod.POST)
    public boolean addSelectedTopicToExistingPost(@RequestBody SelectedTopic selectedTopic){
        boolean result=false;
        try{
            if (!selectedTopicService.selectedTopicExists(selectedTopic.getPostId(),selectedTopic.getTopicId())){
                selectedTopicService.createNewSelectedTopic(selectedTopic);
                result=true;
            }
            else {
                System.out.println("Already topic is selected");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  result;
    }


}
