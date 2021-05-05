package com.myblog.intern.controller;

import com.myblog.intern.model.Post;
import com.myblog.intern.model.ReportDetails;
import com.myblog.intern.service.JwtService;
import com.myblog.intern.service.ReportService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getReportByUserId")
    public ResponseEntity<ReportDetails> getReportByUserId(@RequestParam int id)  throws RuntimeException {
        List<ReportDetails> reportDetails=null;
        try {
            reportDetails=reportService.getReportByUserId(id);

        }catch (Exception ex){
            ex.getMessage();
            //return new ResponseEntity<ReportDetails> (reportDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getReportByPostId")
    public ResponseEntity<ReportDetails> getReportByPostId(@RequestParam int id)  throws RuntimeException {
        List<ReportDetails> reportDetails=null;
        try {
            reportDetails=reportService.getReportByPostId(id);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (reportDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getReportByProfileId")
    public ResponseEntity<ReportDetails> getReportByProfileId(@RequestParam int id)  throws RuntimeException {
        List<ReportDetails> reportDetails=null;
        try {
            reportDetails=reportService.getReportByProfileId(id);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (reportDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllReport")
    public ResponseEntity<ReportDetails> getAllReport()  throws RuntimeException {
        List<ReportDetails> reportDetails=null;
        try {
            reportDetails=reportService.getAll();

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (reportDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllReportByLoggedInUser")
    public ResponseEntity<ReportDetails> getAllReportByLogInUser(HttpServletRequest request)  throws RuntimeException {
        List<ReportDetails> reportDetails=null;
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        try {
            reportDetails=reportService.getReportByUserId(userIdRequest);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (reportDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportDetails, HttpStatus.OK);
    }

    @PostMapping("/addReport")
    public ResponseEntity<String> addOrUpdate(@RequestBody ReportDetails reportDetails, HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        if(reportDetails.getUserId()==null){
            reportDetails.setUserId(userIdRequest);
        }
        else{
            if(userIdRequest!=reportDetails.getUserId())  return new ResponseEntity<String> ("Not a valid user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            reportDetails.setStatus(1);
            reportService.saveReportDetails(reportDetails);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Recorded Report", HttpStatus.OK);
    }


    @PostMapping("/deleteByReportDetailsId")  //to be tested
    public ResponseEntity<String> deleteById(@RequestBody ReportDetails reportDetails, HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        if(userIdRequest!=reportDetails.getUserId())  return new ResponseEntity<String> ("Not a valid user", HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            reportService.deleteById(reportDetails.getId());

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> ("Not Found Report Details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Deleted Report", HttpStatus.OK);
    }


}
