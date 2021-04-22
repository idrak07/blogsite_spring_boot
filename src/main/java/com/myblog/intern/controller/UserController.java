package com.myblog.intern.controller;

import com.myblog.intern.mail.EmailFormat;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.request.LoginRequest;
import com.myblog.intern.request.PasswordChangeRequest;
import com.myblog.intern.request.ResetPasswordRequest;
import com.myblog.intern.request.SignupRequest;
import com.myblog.intern.model.User;
import com.myblog.intern.service.EmailService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Environment environment;
    @Autowired
    EmailService emailService;

    @GetMapping("/hello")
    public void hello(HttpServletResponse response) throws IOException {
        response.sendRedirect("/users");
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
        if(!userService.isValidUserNamePattern(signupRequest.getUserName())) return "Invalid username!!";
        if(userService.userNameExist(signupRequest.getUserName())) return "username is taken!";
        if(userService.emailExist(signupRequest.getEmail())) return "Email already exists!";
        userService.addUser(signupRequest);
        return "Registration Successful!";
    }
    @GetMapping("/users")
    public List<User> users(){
        return userService.fetchAllUsers();
    }

    @PostMapping("/reset-password")
    public String forgetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws UnknownHostException, ParseException {
        Optional<User> user= userService.fetchByCredential(resetPasswordRequest.getCredential());
        System.out.println(emailService.sendMail(userService.getUserDetails(user.get())));
        if(user.isPresent()){
            return "Password Reset Link: "+Inet4Address.getLocalHost().getHostAddress()+":"+environment.getProperty("local.server.port")+"/reset-password/"+user.get().getUserId();
        }
        return "Invalid username or email!";
    }
    @PostMapping("/reset-password/{userId}")
    public String resetPassword(@PathVariable int userId, @RequestBody PasswordChangeRequest passwordChangeRequest){
        return userService.updatePassword(userId, passwordChangeRequest);
    }
}
