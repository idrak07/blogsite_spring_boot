package com.myblog.intern.model;
import java.sql.Date;

public class Post {

    private Integer id;
    private Integer userId;
    private Integer selTopicId;
    private Date date;
    private String details;
    private Integer active;
    private String images;

    public Post() {
    }

    public Post(Integer id, Integer userId, Integer selTopicId, Date date, String details, Integer active, String images) {
        this.id = id;
        this.userId = userId;
        this.selTopicId = selTopicId;
        this.date = date;
        this.details = details;
        this.active = active;
        this.images = images;
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

    public Integer getSelTopicId() {
        return selTopicId;
    }

    public void setSelTopicId(Integer selTopicId) {
        this.selTopicId = selTopicId;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", selTopicId=" + selTopicId +
                ", date=" + date +
                ", details='" + details + '\'' +
                ", active=" + active +
                ", images='" + images + '\'' +
                '}';
    }
}
