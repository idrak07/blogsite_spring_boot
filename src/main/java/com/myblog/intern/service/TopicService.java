package com.myblog.intern.service;

import com.myblog.intern.model.Topic;
import com.myblog.intern.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Topic> getTopicList(){
        List<Topic> topics=null;
        try{
            topics= topicRepository.findAll();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  topics;
    }

    public boolean topicExists(List<Integer> topicIds){
        boolean flag=false;
        for (int i=0;i<topicIds.size();i++){
            if (topicRepository.existsById(topicIds.get(i))){
                flag=true;
            }
            else {
                flag=false;
            }
        }
        return flag;
    }

    public Topic saveUpdateTopic(Topic topic) {
        Topic t=topicRepository.save(topic);
        return t;
    }

    public Topic getTopicById(int id) {
        return topicRepository.findById(id).orElse(null);

    }

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();

    }

    public boolean DeleteTopic(Topic topic) {
        topicRepository.deleteById(topic.getId().intValue());
        return true;
    }

    public boolean topicExist(String description){
        Optional<Topic> topic=topicRepository.findByDescription(description);
        if(topic.isPresent()) return true;
        return false;
    }
}
