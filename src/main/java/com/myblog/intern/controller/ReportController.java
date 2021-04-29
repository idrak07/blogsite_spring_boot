package com.myblog.intern.controller;

import com.myblog.intern.model.ReportDetails;
import com.myblog.intern.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

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

    @PostMapping("/addReport")
    public ResponseEntity<String> addOrUpdate(@RequestBody ReportDetails reportDetails)  throws RuntimeException {
        try {
            reportService.saveReportDetails(reportDetails);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Recorded Report", HttpStatus.OK);
    }

    @PostMapping("/deleteByReportDetailsId")  //to be tested
    public ResponseEntity<String> deleteById(@RequestBody ReportDetails reportDetails)  throws RuntimeException {
        try {
            reportService.deleteById(reportDetails.getId());

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> ("Not Found Report Details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Deleted Report", HttpStatus.OK);
    }


}
