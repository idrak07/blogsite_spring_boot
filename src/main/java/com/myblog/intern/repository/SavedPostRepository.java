package com.myblog.intern.repository;

import com.myblog.intern.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavedPostRepository extends JpaRepository<SavedPost,Integer> {
    @Query(value = "select post_id from saved_post where user_id=?1", nativeQuery = true)
    List<Integer> findPostIdsByUserId(Integer userId);

    SavedPost findSavedPostByUserIdAndPostId(Integer userId, Integer postId);
}
