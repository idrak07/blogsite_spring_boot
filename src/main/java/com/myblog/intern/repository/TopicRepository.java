package com.myblog.intern.repository;

import com.myblog.intern.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Optional<Topic> findByDescription(String description);
}
