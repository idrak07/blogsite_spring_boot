package com.myblog.intern.controller;

import com.myblog.intern.model.*;
import com.myblog.intern.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    SelectedTopicService selectedTopicService;
    @Autowired
    PostSequenceService postSequenceService;
    @Autowired
    TopicService topicService;
    @Autowired
    PostWithTopicService postWithTopicService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;
    @Autowired
    JwtService jwtService;

    /*This method provide new post create option for user
    * Url: localhost:8080/post/create
    * Data :
    * At first check you have a user with user id with value 1 in user table
    * Check you have topic in topic table
    * {"description": "Technology"}, {"description": "Agriculture"}
    * {"userId":1,"title":"My third post","shortDescription": "This is short desc","details":"+wmnembjn+j+keb+e+%0D%0Alnrel%0D%0A%0D%0Aljetnlnerl+noiner.%0D%0A","active":1,"images":",nsdvf","topicList": [1,4]}
     * */
    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String createNewPost(@RequestBody PostWithTopic postWithTopic, HttpServletRequest request){
        String result=null;
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userId= userService.getUserIdByUserName(username);
        Integer id=postSequenceService.getNextVal();
        try{
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Post post=new Post(id, userId,date,postWithTopic.getTitle(),postWithTopic.getShortDescription(),postWithTopic.getDetails(),postWithTopic.getActive(),date,0,0,0);
            if (topicService.topicExists(postWithTopic.getTopicList())){
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
            else{
                result= "Sorry! Topic doesn't exist";
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
    public boolean updatePost(@PathVariable Integer postId, @RequestBody Post post, HttpServletRequest request){
        boolean flag=false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        try {
            if (postService.postExists(postId)){
                if (postService.updatePost(request,postId,post.getTitle(),post.getShortDescription(),post.getDetails(),updateTime)){
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
    public boolean deletePost(@PathVariable Integer postId, HttpServletRequest request){
        boolean flag=false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        try {
            if (postService.postExists(postId)){
                if (postService.deletePost(request, postId,updateTime)){
                    flag=true;
                }
            }
        }
        catch (Exception e){
            System.out.println("Controller: PostController , Method: updatePost, Error: "+e.getMessage());
        }
        return flag;
    }





    /*This method is used to get specific post
    * Url: localhost:8080/post/12
    * Data :Change value 12 for different result as it is post id
    * */
    @RequestMapping(value = "/post/{postId}")
    public CompletePost getPost(@PathVariable Integer postId){

        CompletePost completePost=null;
      try{
         if (postService.postExists(postId)){
             Post post= postService.getPostById(postId);
             List<Integer> selectedTopic= selectedTopicService.getSelectedTopicByPostId(postId);
             List<Comment> commentList= commentService.getCommentByPostId(postId);
             completePost=new CompletePost(post.getId(),post.getUserId(),post.getDate(),post.getTitle(),post.getShortDescription(),post.getDetails(),post.getActive(),post.getUpdatedAt(),post.getView(),post.getLikes(),post.getComments(),selectedTopic,commentList);
         }
      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
        return completePost;
    }

    /*This method is used for removing selected topic from a post
    * Url: localhost:8080/post/13/remove/topic/5
    * Change value 13 for postId and 5 for topicId
    * */
    @RequestMapping(value = "/post/{postId}/remove/topic/{topicId}")
    public boolean removeSelectedTopic(@PathVariable Integer postId,@PathVariable Integer topicId, HttpServletRequest request){
        boolean result=false;
        if(postService.hasPermission(request, postId)){
            try{
                if (selectedTopicService.delSelectedTopic(postId,topicId)){
                    result=true;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return  result;
    }

    /*This method is used for removing selected topic from a post
    * Url: localhost:8080/post/13/remove/topic/5
    * Change value 13 for postId and 5 for topicId
    * */
    @RequestMapping(value = "/post/add/topic", method = RequestMethod.POST)
    public boolean addSelectedTopicToExistingPost(@RequestBody SelectedTopic selectedTopic, HttpServletRequest request){
        boolean result=false;
        if(postService.hasPermission(request, selectedTopic.getPostId())){
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
        }
        return  result;
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

    /*Most Viewed Post list
     * Url: localhost:8080/posts/categories/most-viewed
     * Method:get
     * Data: no data required
     * */
    @RequestMapping(value = "/posts/categories/most-viewed", method = RequestMethod.GET)
    public List<PostWithTopic> getMostViewedPostList(){
        /*List<Post> posts;*/
        List<PostWithTopic> postWithTopics=new ArrayList<>();
        try {
            /*posts=postService.getRecentPosts();*/
            postWithTopics=postWithTopicService.getPostsWithTopic(postService.mostViewedPost());
        }
        catch (Exception e){
            System.out.println("Controller: PostController, Method: getRecentPostList, Error: "+e.getMessage());
        }
        return  postWithTopics;
    }

    @RequestMapping(value = "/post/{postId}/comment/add", method = RequestMethod.POST)
    public boolean addNewComment(@PathVariable Integer postId, @RequestBody Comment comment){
        boolean flag=false;
        try {
            if (postService.postExists(postId)){
                if (userService.userExists(comment.getUserId())){
                    comment.setActive(1);
                    comment.setPostId(postId);
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Timestamp date = new Timestamp(System.currentTimeMillis());
                    comment.setDate(date);
                    if (commentService.addNewComment(comment)){
                        flag=true;
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Controller: PostController, Method: addNewComment, Error: "+e.getMessage());
        }
        return flag;
    }



    @RequestMapping(value = "/post/{postId}/like/add", method = RequestMethod.GET)
    public boolean addNewLike(@PathVariable Integer postId, HttpServletRequest request){
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        boolean flag=false;
        Integer userId;
        try {
            if (postService.postExists(postId)){
                userId=userService.getUserIdByUserName(username);
                if (userService.userExists(userId)){
                    if(!likeService.likeExists(userId,postId)){
                        if (likeService.addLike(new Like(null,userId,postId))){
                            flag=true;
                        }
                    }
                    else{
                        likeService.removeLike(new Like(null,userId,postId));
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Controller: PostController, Method: addNewComment, Error: "+e.getMessage());
        }
        return flag;
    }







}
