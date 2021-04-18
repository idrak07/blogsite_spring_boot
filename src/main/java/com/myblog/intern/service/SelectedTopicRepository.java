package com.myblog.intern.service;

import com.myblog.intern.model.SelectedTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedTopicRepository extends JpaRepository<SelectedTopic, Integer> {
}
