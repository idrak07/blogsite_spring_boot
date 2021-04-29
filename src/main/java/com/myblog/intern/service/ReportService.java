package com.myblog.intern.service;

import com.myblog.intern.Enum.Report_Type;
import com.myblog.intern.model.ReportDetails;
import com.myblog.intern.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public ReportDetails saveReportDetails(ReportDetails reportDetails) {
        ReportDetails u=  reportRepository.save(reportDetails);
        return reportDetails;
    }

    public List<ReportDetails>  getReportByUserId (int UserId){
        List<ReportDetails>  reportDetails=reportRepository.findByUserId(UserId);
        for(int i=0;i<reportDetails.size();i++){
            reportDetails.get(i).setReportType((Report_Type.values())[reportDetails.get(i).getTypeId()]);
        }
        return reportDetails;
    }

    public ReportDetails  getReportById (int reportId){
        ReportDetails reportDetails=reportRepository.findById(reportId).get();
        reportDetails.setReportType((Report_Type.values())[reportDetails.getTypeId()]);
        return reportDetails;
    }

    public List<ReportDetails> getReportByPostId (int postId){
        List<ReportDetails> reportDetails=reportRepository.findByPostId(postId);
        for(int i=0;i<reportDetails.size();i++){
            reportDetails.get(i).setReportType((Report_Type.values())[reportDetails.get(i).getTypeId()]);
        }
        return reportDetails;
    }

    public List<ReportDetails> getReportByProfileId (int profileId){
        List<ReportDetails> reportDetails=reportRepository.findByProfileId(profileId);
        for(int i=0;i<reportDetails.size();i++){
            reportDetails.get(i).setReportType((Report_Type.values())[reportDetails.get(i).getTypeId()]);
        }
        return reportDetails;
    }

    public  List<ReportDetails> getAll(){
        List<ReportDetails> reportDetails=reportRepository.findAll();
        for(int i=0;i<reportDetails.size();i++){
           reportDetails.get(i).setReportType((Report_Type.values())[reportDetails.get(i).getTypeId()]);
        }

        return reportDetails;
    }

    public void  deleteById (int id){
      reportRepository.deleteReportDetailsById(id);

    }
}
