package com.myblog.intern.model;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/*This model is used to bind post and selected topic */
public class PostWithTopic {
    private Integer id;
    private Integer userId;
    private Timestamp date;
    private String title;
    private String shortDescription;
    private String details;
    private Integer active;
    private Timestamp updatedAt;
    private Integer view;
    private Integer likes;
    private Integer comments;
    private List<Integer>topicList;


    public PostWithTopic() {
    }

    public PostWithTopic(Integer id, Integer userId, Timestamp date, String title, String shortDescription, String details, Integer active, Timestamp updatedAt, Integer view, Integer likes, Integer comments, List<Integer> topicList) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.title = title;
        this.shortDescription = shortDescription;
        this.details = details;
        this.active = active;
        this.updatedAt = updatedAt;
        this.view = view;
        this.likes = likes;
        this.comments = comments;
        this.topicList = topicList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public List<Integer> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Integer> topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "PostWithTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", details='" + details + '\'' +
                ", active=" + active +
                ", updatedAt=" + updatedAt +
                ", view=" + view +
                ", likes=" + likes +
                ", comments=" + comments +
                ", topicList=" + topicList +
                '}';
    }
}
