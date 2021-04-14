package com.myblog.intern.model;

import java.sql.Date;

public class PostHistory {

    private Integer id;
    private Integer postId;
    private String details;
    private Date date;

    public PostHistory() {
    }

    public PostHistory(Integer id, Integer postId, String details, Date date) {
        this.id = id;
        this.postId = postId;
        this.details = details;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostHistory{" +
                "id=" + id +
                ", postId=" + postId +
                ", details='" + details + '\'' +
                ", date=" + date +
                '}';
    }
}
