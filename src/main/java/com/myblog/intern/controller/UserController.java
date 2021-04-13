package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @RequestMapping("/users")
    public List<User> fetchUsers(){
        return userService.fetchAllUsers();
    }
}
