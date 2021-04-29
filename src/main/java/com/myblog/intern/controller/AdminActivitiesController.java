package com.myblog.intern.controller;

import com.myblog.intern.model.AdminActivities;
import com.myblog.intern.model.ReportDetails;
import com.myblog.intern.service.AdminActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminActivities")
public class AdminActivitiesController {
    @Autowired
    AdminActivitiesService adminActivitiesService;
    @PostMapping("/addAdminActivities")
    public ResponseEntity<String> addAdmin(@RequestBody AdminActivities adminActivities)  throws RuntimeException {
        try {
            adminActivitiesService.saveUpdateAdminActivities(adminActivities);
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully added AdminActivities ", HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAdminActivities")
    public ResponseEntity<AdminActivities> getAllReport()  throws RuntimeException {
        List<AdminActivities> adminActivities=null;
        try {
            adminActivities=adminActivitiesService.getAllAdminActivities();

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (adminActivities, HttpStatus.OK);
    }
    @GetMapping(value = "/getAdminActivitiesByAdminId")
    public ResponseEntity<AdminActivities> getAllReport(@RequestParam int id)  throws RuntimeException {
        List<AdminActivities> adminActivities=null;
        try {
            adminActivities=adminActivitiesService.getAllAdminActivitiesByAdminId(id);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (adminActivities, HttpStatus.OK);
    }
}
