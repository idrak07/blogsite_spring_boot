package com.myblog.intern.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.sql.Date;

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
    private Date date;
    @Column(name = "title")
    private String title;
    @Column(name = "details")
    private String details;
    @Column(name = "active")
    private Integer active;
    @Column(name = "images")
    private String images;

    public Post() {
    }

    public Post(Integer id, Integer userId, Date date, String title, String details, Integer active, String images) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.title = title;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", date=" + date +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", active=" + active +
                ", images='" + images + '\'' +
                '}';
    }
}
