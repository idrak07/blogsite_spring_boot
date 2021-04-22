package com.myblog.intern.service;

import com.myblog.intern.mail.EmailFormat;
import com.myblog.intern.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailFormat emailFormat;

    public String sendMail(UserDetails userDetails) throws ParseException {
        emailFormat.setValues(userDetails);
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(emailFormat.getFrom());
        message.setTo(emailFormat.getTo());
        message.setSubject(emailFormat.getSubject());
        message.setText(emailFormat.getBody());
        javaMailSender.send(message);
        return "Check your email for verification!";
    }
}
