package com.myblog.intern.mail;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;

public class EmailFormat {
    @Value("${spring.mail.username}")
    @NotNull
    private String from="demoblogsite.intern@gmail.com";
    @NotNull
    private String to;
    private String subject="";
    private String body;

    public EmailFormat(UserDetails userDetails){
        this.to = userDetails.getEmail();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        this.subject="BlogSite Reset Password";
        this.body= "Hi "+userDetails.getFirstName()+",\n"+"\nYour BlogSite password is requested to change on "+today.getTime()
                +".If you did this, you can safely reset your password with this link:\n"
                +"\nIf you didn't do this, please secure your account in this link:.\n" +
                "\nThanks,\n" +
                "The BlogSite Security Team";
        //System.out.println(getFrom()+"; "+getTo());
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
