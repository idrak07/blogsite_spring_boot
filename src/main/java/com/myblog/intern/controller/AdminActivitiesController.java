package com.myblog.intern.controller;

import com.myblog.intern.model.AdminActivities;
import com.myblog.intern.model.ReportDetails;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.service.AdminActivitiesService;
import com.myblog.intern.service.JwtService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/adminActivities")
public class AdminActivitiesController {
    @Autowired
    AdminActivitiesService adminActivitiesService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @PostMapping("/addAdminActivities")
    public ResponseEntity<String> addAdminActivities(@RequestBody AdminActivities adminActivities, HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        adminActivities.setAdminId(userIdRequest);
        try {
            adminActivitiesService.saveUpdateAdminActivities(adminActivities);
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully added AdminActivities ", HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAdminActivities")
    public ResponseEntity<AdminActivities> getAllActivities()  throws RuntimeException {
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

    @PostMapping("/deleteAdminActivities")
    public ResponseEntity<String> deleteAdminActivities(@RequestBody AdminActivities adminActivities, HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        if(userIdRequest!=adminActivities.getAdminId())  return new ResponseEntity<String> ("Not a valid user", HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            adminActivitiesService.deleteAdminActivities(adminActivities);
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully deleted AdminActivities ", HttpStatus.OK);
    }
}
