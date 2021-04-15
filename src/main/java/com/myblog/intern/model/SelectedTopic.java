package com.myblog.intern.model;

public class SelectedTopic {
    private Integer id;
    private Integer topicId;
    private Integer postId;

    public SelectedTopic() {
    }

    public SelectedTopic(Integer id, Integer topicId, Integer postId) {
        this.id = id;
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
