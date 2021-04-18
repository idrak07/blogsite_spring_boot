package com.myblog.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "selected_topic")
public class SelectedTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "topic_id")
    private Integer topicId;
    @Column(name = "post_id")
    private Integer postId;

    public SelectedTopic() {
    }

    public SelectedTopic( Integer topicId, Integer postId) {
        this.topicId = topicId;
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "SelectedTopic{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", postId=" + postId +
                '}';
    }


}
