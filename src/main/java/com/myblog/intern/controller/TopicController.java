package com.myblog.intern.controller;

import com.myblog.intern.model.Topic;
import com.myblog.intern.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @PostMapping("/addTopic")
    public ResponseEntity<String> addOrUpdateTopic(@RequestBody Topic topic)  throws RuntimeException {
        try {
            topicService.saveUpdateTopic(topic);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully added topic", HttpStatus.OK);
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<Topic> getTopicById(@RequestParam int id)  throws RuntimeException {
        Topic topic=null;
        try {
            topic=topicService.getTopicById(id);

        }catch (Exception ex){
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Topic> (topic, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllTopic")
    public ResponseEntity<List<Topic>>  getAllTopics()  throws RuntimeException {
        List<Topic> topics=null;
        try {
            topics=topicService.getAllTopic();

        }catch (Exception ex){
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @PostMapping("/deleteTopic")
    public ResponseEntity<String> deleteTopic(@RequestBody Topic topic)  throws RuntimeException {
        try {
            topicService.DeleteTopic(topic);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully deleted topic", HttpStatus.OK);
    }

    /*This method is used for getting topic description by id
     * If it is not found then it is returning null
     * Url: localhost:8080/topic/desc/5
     * Make sure you have data in topic table
     * Change value 1 for different testing
     * */
    @RequestMapping(value = "/desc/{id}", method = RequestMethod.GET)
    public String getSpecificTopicDesc(@PathVariable Integer id){
        return  topicService.getTopicDescById(id);
    }


    /*This method is used for getting all topics
     * User usage: when creating post
     * Url: localhost:8080/topic/all
     * */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopic(){
        return ResponseEntity.ok(topicService.getTopicList());
    }
}
