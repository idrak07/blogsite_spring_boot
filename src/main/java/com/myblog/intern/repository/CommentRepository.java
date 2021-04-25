package com.myblog.intern.repository;

import com.myblog.intern.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentsByPostIdOrderByDateDesc(Integer postId);
}
