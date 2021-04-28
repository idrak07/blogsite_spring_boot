package com.myblog.intern.service;

import com.myblog.intern.model.Topic;
import com.myblog.intern.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
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
}
