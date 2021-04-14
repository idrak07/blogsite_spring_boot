package com.myblog.intern.model;

import java.sql.Date;

public class AdminActivities {

    private Integer id;
    private Integer adminId;
    private Integer reportId;
    private Date time;
    private String details;

    public AdminActivities() {
    }

    public AdminActivities(Integer id, Integer adminId, Integer reportId, Date time, String details) {
        this.id = id;
        this.adminId = adminId;
        this.reportId = reportId;
        this.time = time;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "AdminActivities{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", reportId=" + reportId +
                ", time=" + time +
                ", details='" + details + '\'' +
                '}';
    }
}
