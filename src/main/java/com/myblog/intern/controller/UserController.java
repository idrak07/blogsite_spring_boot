package com.myblog.intern.controller;

import com.myblog.intern.mail.EmailFormat;
import com.myblog.intern.model.Token;
import com.myblog.intern.request.LoginRequest;
import com.myblog.intern.request.PasswordChangeRequest;
import com.myblog.intern.request.ResetPasswordRequest;
import com.myblog.intern.request.SignupRequest;
import com.myblog.intern.model.User;
import com.myblog.intern.response.AuthenticationResponse;
import com.myblog.intern.service.EmailService;
import com.myblog.intern.service.SecurityTokenService;
import com.myblog.intern.service.TokenService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    TokenService tokenService;
    @Autowired
    private UserDetailsService userDetailsService;

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
        return userService.addUser(signupRequest);
    }
    @GetMapping("/users")
    public List<User> users(){
        return userService.fetchAllUsers();
    }

    @RequestMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws ParseException, UnknownHostException {
        Optional<User> user= userService.fetchByCredential(resetPasswordRequest.getCredential());
        if(!user.isPresent()) return ResponseEntity.ok("Invalid username or email!");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.get().getUserName());
        //final String securityToken = securityTokenService.generateToken(userDetails);
        return ResponseEntity.ok(emailService.sendMail(userService.getUserDetails(user.get()), user.get().getUserName()));
    }

    /*@GetMapping("/security-question")
    public ResponseEntity<?> securityQuestion(HttpServletRequest request){
        String userName=securityTokenService.extractUserName(securityTokenService.parseToken(request));
        Optional<User> user= userService.fetchByCredential(userName);
        if(!user.isPresent()) return ResponseEntity.badRequest().body("Malicious Request!");
        com.myblog.intern.model.UserDetails userDetails= userService.getUserDetails(user.get());
        return ResponseEntity.ok(userDetails.getSecurityQuestion());
    }
    @PostMapping("/security-answer")
    public ResponseEntity<?> securityAnswer(HttpServletRequest request, @RequestBody String securityAnswer){
        String userName=securityTokenService.extractUserName(securityTokenService.parseToken(request));
        Optional<User> user= userService.fetchByCredential(userName);
        if(!user.isPresent()) return ResponseEntity.badRequest().body("Malicious Request!");
        com.myblog.intern.model.UserDetails userDetails= userService.getUserDetails(user.get());
        if(securityAnswer.equals(userDetails.getSecurityAnswer())) return ResponseEntity.ok("Check your email for Password-reset url!");
        return ResponseEntity.badRequest().body("Answer is incorrect!");
    }

    @PostMapping("/reset-password-testing")
    public String forgetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws UnknownHostException, ParseException {
        Optional<User> user= userService.fetchByCredential(resetPasswordRequest.getCredential());
        System.out.println(emailService.sendMail(userService.getUserDetails(user.get()), user.get().getUserName()));
        if(user.isPresent()){
            return "Password Reset Link: "+Inet4Address.getLocalHost().getHostAddress()+":"+environment.getProperty("local.server.port")+"/reset-password/"+user.get().getUserId();
        }
        return "Invalid username or email!";
    }*/

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody PasswordChangeRequest passwordChangeRequest){
        Optional<Token> tokenObj=tokenService.getToken(token);
        if(!tokenObj.isPresent()) return ResponseEntity.badRequest().body("Malicious Request!");
        if(tokenObj.get().isExpire()) return ResponseEntity.badRequest().body("The request has been expired!");
        Optional<User> user=userService.fetchByCredential(tokenObj.get().getUserName());
        com.myblog.intern.model.UserDetails userDetails= userService.getUserDetails(user.get());
        //tokenService.deleteByUserName(user.get().getUserName());
        return ResponseEntity.ok(userService.updatePassword(userDetails.getUserId(), passwordChangeRequest));
    }

    @RequestMapping("/admin-panel")
    public String adminPanel(){
        return "Admin!";
    }

}
