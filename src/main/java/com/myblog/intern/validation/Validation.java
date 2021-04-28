package com.myblog.intern.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {


    public Validation() {
    }


    public boolean isValidEmailPattern(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }


    public boolean isValidUserNamePattern(String userName){
        String regex = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(userName);
        return matcher.matches();
    }
}


