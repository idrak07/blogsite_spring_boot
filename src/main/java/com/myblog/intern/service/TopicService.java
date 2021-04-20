package com.myblog.intern.service;

import com.myblog.intern.model.Topic;
import com.myblog.intern.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    public String getTopicDescById(Integer id){
        String desc= null;
        try{
            desc=topicRepository.getOne(id).getDescription();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return desc;
    }


}
