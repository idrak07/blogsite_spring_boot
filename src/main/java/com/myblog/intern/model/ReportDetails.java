package com.myblog.intern.model;


import com.myblog.intern.Enum.Report_Type;

import javax.persistence.*;

@Entity
public class ReportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "status")
    private Integer status;

    @Enumerated(EnumType.ORDINAL)
    private Report_Type reportType;

    public ReportDetails() {

    }

    public ReportDetails(Integer id, Integer typeId, Integer userId, Integer postId, Integer profileId, Integer status) {
        this.id = id;
        this.typeId = typeId;
        this.userId = userId;
        this.postId = postId;
        this.profileId = profileId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Report_Type getReportType() {
        return reportType;
    }

    public void setReportType(Report_Type reportType) {
        this.reportType = reportType;
    }

    @Override
    public String toString() {
        return "ReportDetails{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", profileId=" + profileId +
                ", status=" + status +
                '}';
    }
}
