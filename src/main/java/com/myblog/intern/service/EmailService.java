package com.myblog.intern.service;

import com.myblog.intern.mail.EmailFormat;
import com.myblog.intern.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.springframework.core.env.Environment;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailFormat emailFormat;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private Environment environment;

    public String sendMail(UserDetails userDetails, String userName) throws ParseException, UnknownHostException {
        if(!tokenService.createToken(userName)) return "We've already sent an reset-link!";
        String url=tokenService.getTokenValue();
        url= Inet4Address.getLocalHost().getHostAddress()+":"+environment.getProperty("local.server.port")+"/reset-password/"+url;
        emailFormat.setValues(userDetails, url);
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(emailFormat.getFrom());
        message.setTo(emailFormat.getTo());
        message.setSubject(emailFormat.getSubject());
        message.setText(emailFormat.getBody());
        System.out.println(emailFormat.getFrom()+" "+emailFormat.getTo());
        javaMailSender.send(message);
        return "Check your email for password reset link.";
    }
}
