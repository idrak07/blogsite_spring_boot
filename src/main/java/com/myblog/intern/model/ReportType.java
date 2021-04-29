package com.myblog.intern.model;

import com.myblog.intern.Enum.Report_Type;

import javax.persistence.*;

@Entity
public class ReportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "type")
//    private String type;
    @Enumerated(EnumType.STRING)
    private Report_Type type;

    public ReportType() {
    }

    public ReportType(Integer id, Report_Type type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Report_Type getType() {
        return type;
    }

    public void setType(Report_Type type) {
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
