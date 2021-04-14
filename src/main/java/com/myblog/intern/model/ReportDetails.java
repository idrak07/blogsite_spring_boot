package com.myblog.intern.model;

public class ReportDetails {
    private  Integer id;
    private Integer typeId;
    private Integer userId;
    private Integer postId;
    private Integer profileId;
    private Integer status;

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
