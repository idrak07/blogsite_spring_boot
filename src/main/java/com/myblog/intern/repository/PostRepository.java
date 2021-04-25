package com.myblog.intern.repository;


import com.myblog.intern.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostByUserIdAndDateAndTitleAndDetails(Integer userId, Timestamp date, String title, String details);

    Post findPostById(Integer id);

    List<Post> findAllByActiveOrderByDateDesc(Integer active);

    List<Post> findAllByActiveOrderByViewDesc(Integer active);

    boolean existsPostByIdAndActive(Integer postId, Integer active);


}
