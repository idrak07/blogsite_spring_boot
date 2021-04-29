package com.myblog.intern.controller;

import com.myblog.intern.model.ReportType;
import com.myblog.intern.service.ReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reportType")
public class ReportTypeController {

    @Autowired
    private ReportTypeService reportTypeService;

    @PostMapping("/addReportType")
    public ResponseEntity<String> addOrUpdate(@RequestBody ReportType reportType)  throws RuntimeException {
        try {
            reportTypeService.saveReportType(reportType);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Recorded Report", HttpStatus.OK);
    }


    @PostMapping("/deleteReportTypeById") //to be tested
    public ResponseEntity<String> delete(@RequestBody ReportType reportType)  throws RuntimeException {
        try {
            reportTypeService.deleteReportTypeById(reportType);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Recorded Report", HttpStatus.OK);
    }

    @GetMapping(value = "/getAllReportType")
    public ResponseEntity<ReportType> getAllReportType()  throws RuntimeException {
        List<ReportType> reportTypes=null;
        try {
            reportTypes=reportTypeService.getAllReportTypes();

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (reportTypes, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportTypes, HttpStatus.OK);
    }

    @GetMapping(value = "/getReportTypeById")
    public ResponseEntity<ReportType> getReportTypeById(@RequestParam int id)  throws RuntimeException {
        Optional<ReportType> reportType=null;
        try {
            reportType=reportTypeService.getReportTypeById(id);

        }catch (Exception ex){
              ex.getMessage();
            return new ResponseEntity (reportType, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (reportType, HttpStatus.OK);
    }
}
