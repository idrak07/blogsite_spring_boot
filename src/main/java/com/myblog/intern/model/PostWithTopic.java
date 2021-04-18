package com.myblog.intern.model;

import javax.persistence.Column;
import java.sql.Date;
import java.util.Arrays;

public class PostWithTopic {
    private Integer id;
    private Integer userId;
    private Date date;
    private String details;
    private Integer active;
    private String images;
    private Integer []topicList;

    public PostWithTopic() {
    }

    public PostWithTopic(Integer id, Integer userId, Date date, String details, Integer active, String images, Integer[] topicList) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.details = details;
        this.active = active;
        this.images = images;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer[] getTopicList() {
        return topicList;
    }

    public void setTopicList(Integer[] topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "PostWithTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", details='" + details + '\'' +
                ", active=" + active +
                ", images='" + images + '\'' +
                ", topicList=" + Arrays.toString(topicList) +
                '}';
    }
}
