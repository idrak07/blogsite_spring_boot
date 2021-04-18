package com.myblog.intern.repository;

import com.myblog.intern.model.SelectedTopic;
import com.myblog.intern.service.SelectedTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectedTopicService {

    @Autowired
    SelectedTopicRepository selectedTopicRepository;

    public void createNewSelectedTopic(SelectedTopic selectedTopic){
        selectedTopicRepository.save(selectedTopic);
        System.out.println("Db inserted:"+ selectedTopic.getTopicId()+", "+selectedTopic.getPostId());
    }

}
