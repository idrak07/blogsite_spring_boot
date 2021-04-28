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
}
