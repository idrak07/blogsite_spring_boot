package com.myblog.intern.repository;

import com.myblog.intern.model.ReportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportDetails, Integer> {
    public List<ReportDetails>  findByUserId(Integer id);
    public   List<ReportDetails> findByPostId(Integer id);
    public   List<ReportDetails> findByProfileId(Integer id);
    @Transactional
    public long deleteReportDetailsById(int id);

}
