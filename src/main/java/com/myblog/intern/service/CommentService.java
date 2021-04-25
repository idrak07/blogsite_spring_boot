package com.myblog.intern.service;

import com.myblog.intern.model.Comment;
import com.myblog.intern.model.Post;
import com.myblog.intern.repository.CommentRepository;
import com.myblog.intern.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public boolean addNewComment(Comment comment){
        boolean flag= false;
        try {
            commentRepository.save(comment);
            Post post=postRepository.getOne(comment.getPostId());
            post.setComments(post.getComments()+1);
            postRepository.save(post);
            flag=true;
        }
        catch (Exception e){
            System.out.println("Service: CommentService, Method: addNewComment, Error:"+e.getMessage());
        }
        return flag;
    }

    public List<Comment> getCommentByPostId(Integer postId) {
        List<Comment> commentList= new ArrayList<>();
        try{
            commentList=commentRepository.findCommentsByPostIdOrderByDateDesc(postId);
        }
        catch (Exception e){
            System.out.println("Service: CommentService, Method: getCommentByPostId, Error:"+e.getMessage());
        }
        return commentList;
    }
}
