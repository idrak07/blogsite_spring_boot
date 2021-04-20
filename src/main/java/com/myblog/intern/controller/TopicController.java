package com.myblog.intern.controller;

import com.myblog.intern.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    /*This method is used to get Topic description
    * Url: localhost:8080/topic/1
    * Method :Get
    * Change value 1 for different result
    * */
    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/topic/desc/{id}", method = RequestMethod.GET)
    public String getSpecificTopicDesc(@PathVariable Integer id){
        return  topicService.getTopicDescById(id);
    }
}
