package com.myblog.intern.controller;

import com.myblog.intern.model.Topic;
import com.myblog.intern.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    TopicService topicService;
    /*This method is used for getting topic description by id
    * If it is not found then it is returning null
    * Url: localhost:8080/topic/desc/5
    * Make sure you have data in topic table
    * Change value 1 for different testing
    * */
    @RequestMapping(value = "/topic/desc/{id}", method = RequestMethod.GET)
    public String getSpecificTopicDesc(@PathVariable Integer id){
        return  topicService.getTopicDescById(id);
    }


    /*This method is used for getting all topics
    * User usage: when creating post
    * Url: localhost:8080/topic/all
    * */
    @RequestMapping(value = "/topic/all", method = RequestMethod.GET)
    public List<Topic> getAllTopic(){
        return  topicService.getTopicList();
    }





}
