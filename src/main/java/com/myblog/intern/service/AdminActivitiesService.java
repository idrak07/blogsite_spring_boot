package com.myblog.intern.service;

import com.myblog.intern.Enum.Report_Type;
import com.myblog.intern.model.*;
import com.myblog.intern.repository.AdminActivitiesRepo;
import com.myblog.intern.repository.PostRepository;
import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;

@Service
public class AdminActivitiesService {
    @Autowired
    AdminActivitiesRepo adminActivitiesRepo;
    @Autowired
    ReportService reportService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    public AdminActivities saveUpdateAdminActivities(AdminActivities adminActivities) {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        adminActivities.setTime(date);
        ReportDetails reportDetails=reportService.getReportById(adminActivities.getReportId());
        if(reportDetails.getReportType()==Report_Type.PROFILE_REPORT){  //profile report
            User user=userRepository.findById(reportDetails.getUserId()).get();
            user.setActive(false);
            userRepository.save(user);
            adminActivities.setDetails("User Profile Deactivated");
        }else if(reportDetails.getReportType()==Report_Type.POST_REPORT){ //post report
            Post post=postRepository.findById(reportDetails.getPostId()).get();
            post.setActive(0);
            postRepository.save(post);
            adminActivities.setDetails("User Post Deactivated");
        }
        reportDetails.setStatus(2); // 2 denotes action taken regarding report type
        adminActivitiesRepo.save(adminActivities);
        reportService.saveReportDetails(reportDetails);
        return adminActivities;
    }

    public List<AdminActivities> getAllAdminActivities (){
        List<AdminActivities>adminActivities=adminActivitiesRepo.findAll();
        return adminActivities;
    }

    public List<AdminActivities> getAllAdminActivitiesByAdminId (int adminId){
        List<AdminActivities>adminActivities=adminActivitiesRepo.findByAdminId(adminId);
        return adminActivities;
    }

    public AdminActivities deleteAdminActivities(AdminActivities adminActivities){
        adminActivitiesRepo.deleteById(adminActivities.getId());
        return adminActivities;
    }

}
