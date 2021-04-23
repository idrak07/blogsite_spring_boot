package com.myblog.intern.service;

import com.myblog.intern.repository.PostSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostSequenceService {
     @Autowired
    PostSequenceRepository postSequenceRepository;

     public  Integer getNextVal(){
         return postSequenceRepository.findNextVal();
     }
}
