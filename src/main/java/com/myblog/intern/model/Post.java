package com.myblog.intern.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "post_sequence"),
                    @Parameter(name = "initial_value", value = "1001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "post_time")
    private Timestamp date;
    @Column(name = "title")
    private String title;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "details")
    private String details;
    @Column(name = "active")
    private Integer active;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "view")
    private Integer view;

    public Post() {
    }

    public Post(Integer id, Integer userId, Timestamp date, String title, String shortDescription, String details, Integer active, Timestamp updatedAt, Integer view) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.title = title;
        this.shortDescription = shortDescription;
        this.details = details;
        this.active = active;
        this.updatedAt = updatedAt;
        this.view = view;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", details='" + details + '\'' +
                ", active=" + active +
                ", updatedAt=" + updatedAt +
                ", view=" + view +
                '}';
    }
}
