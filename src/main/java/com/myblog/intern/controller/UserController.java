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
        if(userService.userNameExist(signupRequest.getUserName())) return "username is taken!";
        if(userService.emailExist(signupRequest.getEmail())) return "email already exists!";
        return "Registration Successful!";
    }
    @GetMapping("/users")
    public List<User> users(){
        return userService.fetchAllUsers();
    }
}
