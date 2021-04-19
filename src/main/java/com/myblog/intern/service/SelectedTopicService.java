package com.myblog.intern.service;

import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.repository.SelectedTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedTopicService {

    @Autowired
    SelectedTopicRepository selectedTopicRepository;

    public void createNewSelectedTopic(SelectedTopic selectedTopic){
        selectedTopicRepository.save(selectedTopic);
        System.out.println("Db inserted:"+ selectedTopic.getTopicId()+", "+selectedTopic.getPostId());
    }

    public List<Integer> getSelectedTopicByPostId(Integer postId){
        List<Integer> selectedTopics= new ArrayList<>();
        selectedTopicRepository.findTopicId(postId).forEach(selectedTopics::add);
        return selectedTopics;
    }

}
