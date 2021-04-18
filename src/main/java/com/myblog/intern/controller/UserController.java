package com.myblog.intern.controller;

import com.myblog.intern.model.LoginRequest;
import com.myblog.intern.model.SignupRequest;
import com.myblog.intern.model.User;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/registration")
    public String login(@RequestBody LoginRequest loginRequest){
        return userService.Test(loginRequest);
    }
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest){
        if(signupRequest.getUserName().length()==0) return "username can not be empty!";
        if(signupRequest.getFirstName().length()==0 || signupRequest.getLastName().length()==0) return "Name can not be empty!";
        if(signupRequest.getPassword().length()<8) return "Password should contain at least 8 characters!";
        if(!userService.isValidEmailPattern(signupRequest.getEmail())) return "Invalid email!";
        if(userService.userNameExist(signupRequest.getUserName())) return "username is taken!";
        if(userService.emailExist(signupRequest.getEmail())) return "Email already exists!";
        userService.addUser(signupRequest);
        return "Registration Successful!";
    }
    @GetMapping("/users")
    public List<User> users(){
        return userService.fetchAllUsers();
    }
}
