package com.myblog.intern.model;

public class ReportType {
    private Integer id;
    private String type;

    public ReportType() {
    }

    public ReportType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReportType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
