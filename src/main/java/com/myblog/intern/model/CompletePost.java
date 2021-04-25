package com.myblog.intern.model;

import java.sql.Timestamp;
import java.util.List;

public class CompletePost {
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
    private List<Integer> topics;
    private List<Comment> commentList;

    public CompletePost() {
    }

    public CompletePost(Integer id, Integer userId, Timestamp date, String title, String shortDescription, String details, Integer active, Timestamp updatedAt, Integer view, Integer likes, Integer comments, List<Integer> topics, List<Comment> commentList) {
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
        this.topics = topics;
        this.commentList = commentList;
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

    public List<Integer> getTopics() {
        return topics;
    }

    public void setTopics(List<Integer> topics) {
        this.topics = topics;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "CompletePost{" +
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
                ", topics=" + topics +
                ", commentList=" + commentList +
                '}';
    }
}
