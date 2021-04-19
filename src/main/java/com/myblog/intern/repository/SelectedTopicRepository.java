package com.myblog.intern.repository;

import com.myblog.intern.model.SelectedTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedTopicRepository extends JpaRepository<SelectedTopic, Integer> {
    List<SelectedTopic> findSelectedTopicsByPostId(Integer postId);

    @Query(value = "select TOPIC_ID from Selected_topic where post_id=?1", nativeQuery = true)
    List<Integer> findTopicId(Integer postId);
}
