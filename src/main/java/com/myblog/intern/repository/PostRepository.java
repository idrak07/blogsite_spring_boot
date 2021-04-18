package com.myblog.intern.repository;


import com.myblog.intern.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select id from POST where user_id=?1 and post_time=?2 and details=?3", nativeQuery = true)
    Integer findId(Integer userId, Date date, String details);
}
