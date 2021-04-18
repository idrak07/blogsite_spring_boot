package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.request.passwordRequest;
import com.myblog.intern.services.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/changePassword")
public class ChangePasswordController {
    @Autowired
    private ChangePasswordService changePasswordService;
   // private PasswordEncoder passwordEncoder;

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody passwordRequest request)  throws RuntimeException {
        User user;
        try {
        //    String encoded=passwordEncoder.encode(request.getOldPassword());
            user=changePasswordService.getByPassword(request.getOldPassword());

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> ("Password didn't match", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            user.setPassword(request.getNewPassword());
            changePasswordService.saveUpdateProfile(user);
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> ("Password could not change", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Changed Password", HttpStatus.OK);
    }
//    public  boolean  resetPassword(HttpServletRequest request, User user){
//        String newPassword=request.getParameter("newPassword");
//        String oldPassword=request.getParameter("oldPassword");
//        try {
//             user=changePasswordService.getByPassword(oldPassword);
//
//        }catch (Exception ex){
//            ex.getMessage();
//            return false;
//        }
//
//        return true;
//    }

}
