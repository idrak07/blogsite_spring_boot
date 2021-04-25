package com.myblog.intern.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post_history")
public class PostHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "title")
    private String title;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "details")
    private String details;
    @Column(name = "date")
    private Timestamp date;

    public PostHistory() {
    }

    public PostHistory(Integer id, Integer postId, String title, String shortDescription, String details, Timestamp date) {
        this.id = id;
        this.postId = postId;
        this.title = title;
        this.shortDescription = shortDescription;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostHistory{" +
                "id=" + id +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", details='" + details + '\'' +
                ", date=" + date +
                '}';
    }
}
