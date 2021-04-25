package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.service.PostSequenceService;
import com.myblog.intern.service.PostWithTopicService;
import com.myblog.intern.service.SelectedTopicService;
import com.myblog.intern.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    SelectedTopicService selectedTopicService;
    @Autowired
    PostSequenceService postSequenceService;

    @Autowired
    PostWithTopicService postWithTopicService;

    /*This method provide new post create option for user
    * Url: localhost:8080/post/create
    * Data :
    * At first check you have a user with user id with value 1 in user table
    * Check you have topic in topic table
    * {"description": "Technology"}, {"description": "Agriculture"}
    * {"userId":1,"title":"My third post","shortDescription": "This is short desc","details":"+wmnembjn+j+keb+e+%0D%0Alnrel%0D%0A%0D%0Aljetnlnerl+noiner.%0D%0A","active":1,"images":",nsdvf","topicList": [1,4]}
     * */
    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String createNewPost(@RequestBody PostWithTopic postWithTopic){
        String result=null;
        Integer id=postSequenceService.getNextVal();
        try{
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Post post=new Post(id,postWithTopic.getUserId(),date,postWithTopic.getTitle(),postWithTopic.getShortDescription(),postWithTopic.getDetails(),postWithTopic.getActive(),date,0,0,0);

            if(postService.createPost(post)){
                for(int i=0;i<postWithTopic.getTopicList().size();i++){
                    SelectedTopic selectedTopic=new SelectedTopic( postWithTopic.getTopicList().get(i),id);
                    selectedTopicService.createNewSelectedTopic(selectedTopic);
                }
               result= "New post created";
            }
            else{
                result= "Sorry! Problem found";
            }
            
        }
        catch (Exception e){
            System.out.println("Controller: PostController , Method: CreatePost, Error: "+e.getMessage());
        }
        return result;
    }


    /*This method provide post update option for user
     * Url: localhost:8080/post/{postId}/update
     * Data :
     * At first check you have a user with user id with value 1 in user table
     * Check you have topic in topic table
     * {"description": "Technology"}, {"description": "Agriculture"}
     * {"title":"My third post","shortDescription": "This is short desc","details":"+wmnembjn+j+keb+e+%0D%0Alnrel%0D%0A%0D%0Aljetnlnerl+noiner.%0D%0A"}
     * */
    @RequestMapping(value = "/post/{postId}/update", method = RequestMethod.POST)
    public boolean updatePost(@PathVariable Integer postId, @RequestBody Post post){
        boolean flag=false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        try {
            if (postService.postExists(postId)){
                if (postService.updatePost(postId,post.getTitle(),post.getShortDescription(),post.getDetails(),updateTime)){
                    flag=true;
                }
            }
        }
        catch (Exception e){
            System.out.println("Controller: PostController , Method: updatePost, Error: "+e.getMessage());
        }
        return flag;
    }


    /*This method provide new post create option for user
     * Url: localhost:8080/post/{postId}/delete
     * Data : Not needed, only postId
     *  */
    @RequestMapping(value = "/post/{postId}/delete", method = RequestMethod.GET)
    public boolean deletePost(@PathVariable Integer postId){
        boolean flag=false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        try {
            if (postService.postExists(postId)){
                if (postService.deletePost(postId,updateTime)){
                    flag=true;
                }
            }
        }
        catch (Exception e){
            System.out.println("Controller: PostController , Method: updatePost, Error: "+e.getMessage());
        }
        return flag;
    }



    /*Recent Post list
    * Url: localhost:8080/posts/categories/recent
    * Method:get
    * Data: no data required
    * */
    @RequestMapping(value = "/posts/categories/recent")
    public List<PostWithTopic> getRecentPostList(){
        List<Post> posts;
        List<PostWithTopic> postWithTopics=new ArrayList<>();
        try {
            posts=postService.getRecentPosts();
            postWithTopics=postWithTopicService.getPostsWithTopic(posts);
        }
        catch (Exception e){
            System.out.println("Controller: PostController, Method: getRecentPostList, Error: "+e.getMessage());
        }
        return  postWithTopics;

    }




    /*This method is used to get specific post
    * Url: localhost:8080/post/12
    * Data :Change value 12 for different result as it is post id
    * */
    @RequestMapping(value = "/post/{postId}")
    public PostWithTopic getPost(@PathVariable Integer postId){
        PostWithTopic postWithTopic=null;
      try{
         if (postService.postExists(postId)){
             Post post= postService.getPostById(postId);
             List<Integer> selectedTopic= selectedTopicService.getSelectedTopicByPostId(postId);
             postWithTopic=new PostWithTopic(post.getId(),post.getUserId(),post.getDate(),post.getTitle(),post.getShortDescription(),post.getDetails(),post.getActive(),post.getUpdatedAt(),post.getView(),post.getLikes(),post.getComments(),selectedTopic);
         }
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
