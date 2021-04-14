package com.myblog.intern.model;

import java.sql.Date;

public class LoginActivities {
    private Integer loginId;
    private Integer userId;
    private String ipAddress;
    private Date time;

    public LoginActivities() {
    }

    public LoginActivities(Integer loginId, Integer userId, String ipAddress, Date time) {
        this.loginId = loginId;
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.time = time;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LoginActivities{" +
                "loginId=" + loginId +
                ", userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", time=" + time +
                '}';
    }
}
