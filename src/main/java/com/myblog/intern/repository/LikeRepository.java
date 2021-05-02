package com.myblog.intern.repository;

import com.myblog.intern.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    boolean existsLikeByUserIdAndAndPostId(Integer userId, Integer postId);
    boolean deleteByUserIdAAndPostId(Integer userId, Integer postId);
}
