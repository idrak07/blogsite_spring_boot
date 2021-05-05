package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.request.passwordRequest;
import com.myblog.intern.service.ChangePasswordService;
import com.myblog.intern.service.JwtService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/changePassword")
public class ChangePasswordController {
    @Autowired
    private ChangePasswordService changePasswordService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;


    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody passwordRequest request, HttpServletRequest request2) throws RuntimeException {

        if(request.getNewPassword().length()<8)  return new ResponseEntity<String> ("Password should contain at least 8 characters!", HttpStatus.NOT_FOUND);
        if(!(request.getNewPassword().equals(request.getRetypeNewPassword()))) return new ResponseEntity<String>("Retyped Password is wrong", HttpStatus.NOT_FOUND);

        String username= jwtService.extractUserName(jwtService.parseToken(request2));
        User user= userService.getUserByName(username);
//        if(user.getUserId()!=id) return new ResponseEntity<String>("This user is not authenticated", HttpStatus.NOT_FOUND);

        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(request.getOldPassword(),user.getPassword())){
                try {
                    String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
                    user.setPassword(encodedNewPassword);
                    changePasswordService.saveUpdateProfile(user);
                } catch (Exception ex) {
                    ex.getMessage();
                    return new ResponseEntity<String>("Password could not change", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Successfully Changed Password", HttpStatus.OK);

    }
}
