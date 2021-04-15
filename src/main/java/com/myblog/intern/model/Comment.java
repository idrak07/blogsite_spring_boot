package com.myblog.intern.model;

import java.sql.Date;

public class Comment {

    private Integer id;
    private Integer userId;
    private Integer postId;
    private Date date;
    private String details;

    public Comment() {
    }

    public Comment(Integer id, Integer userId, Integer postId, Date date, String details) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
        this.details = details;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", date=" + date +
                ", details='" + details + '\'' +
                '}';
    }
}
