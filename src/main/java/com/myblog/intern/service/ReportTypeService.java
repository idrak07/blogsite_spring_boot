package com.myblog.intern.service;

import com.myblog.intern.model.ReportType;
import com.myblog.intern.repository.ReportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportTypeService {
    @Autowired
    private ReportTypeRepository reportTypeRepository;

    public ReportType saveReportType(ReportType reportType) {
        ReportType u=  reportTypeRepository.save(reportType);
        return u;
    }

    public List<ReportType>  getAllReportTypes(){
        List<ReportType> u=reportTypeRepository.findAll();
        return u;
    }

    public Optional<ReportType> getReportTypeById(int id){
        Optional<ReportType> u=reportTypeRepository.findById(id);
        return u;
    }

    public void deleteReportTypeById(ReportType reportType){
        reportTypeRepository.deleteById(reportType.getId());
    }
}
