package com.myblog.intern.service;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.PostWithTopic;
import com.myblog.intern.repository.SelectedTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostWithTopicService {
    @Autowired
    SelectedTopicService selectedTopicService;

    public List<PostWithTopic> getPostsWithTopic(List<Post> posts){
        List<PostWithTopic> postWithTopics= new ArrayList<>();
        for(int i=0;i<posts.size();i++){
            postWithTopics.add(new PostWithTopic(posts.get(i).getId(),posts.get(i).getUserId(),posts.get(i).getDate(),posts.get(i).getTitle(),posts.get(i).getShortDescription(),posts.get(i).getDetails(),posts.get(i).getActive(),posts.get(i).getUpdatedAt(),posts.get(i).getView(),selectedTopicService.getSelectedTopicByPostId(posts.get(i).getId())));
        }
        return postWithTopics;
    }
}
