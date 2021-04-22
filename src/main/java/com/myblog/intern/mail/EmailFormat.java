package com.myblog.intern.mail;

import com.myblog.intern.model.UserDetails;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class EmailFormat {
    @Value("${spring.mail.username}")
    private String from;
    @NotNull
    private String to;
    private String subject="";
    private String body;

    public EmailFormat(){
    }
    public void setValues(UserDetails userDetails) throws ParseException {
        this.to = userDetails.getEmail();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(System.currentTimeMillis());
        this.subject="BlogSite Reset Password";
        this.body= "Hi "+userDetails.getFirstName()+",\n"+"\nYour BlogSite password is requested to change on "+sdf.parse(sdf.format(date))
                +".If you did this, you can safely reset your password with this link:\n"
                +"\nIf you didn't do this, please secure your account in this link:.\n" +
                "\nThanks,\n" +
                "The BlogSite Security Team";
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFrom() {
        return from;
    }
}
