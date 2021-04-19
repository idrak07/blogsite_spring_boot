package com.myblog.intern.service;

import com.myblog.intern.repository.SavedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPostService {
    @Autowired
    SavedPostRepository savedPostRepository;

    public List<Integer> getPostId(Integer userid){
        return savedPostRepository.findPostIdsByUserId(userid);
    }
}
