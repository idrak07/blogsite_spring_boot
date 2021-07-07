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
        return selectedTopicRepository.findTopicId(postId);

    }
    public boolean selectedTopicExists(Integer postId, Integer topicId) {
        boolean flag = false;
        try {
            if (selectedTopicRepository.findSelectedTopicByTopicIdAndPostId(topicId,postId).getId()!=null){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Method - selectedTopicExists : "+e.getMessage());
        }
        return flag;
    }
    public boolean delSelectedTopic(Integer postId, Integer topicId){
        boolean flag=false;
        SelectedTopic selectedTopic;
        try{
            if (selectedTopicExists(postId,topicId)){
                selectedTopic= selectedTopicRepository.findSelectedTopicByTopicIdAndPostId(topicId,postId);
                selectedTopicRepository.delete(selectedTopic);
                flag=true;
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    public void removeSelectedTopicByPostId(Integer postId){
        selectedTopicRepository.deleteAllByPostId(postId);
    }

}
