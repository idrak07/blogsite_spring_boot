package com.myblog.intern.model;

public class LoginRequest {
    private String username;
    private String password;


    public LoginRequest(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }
    public LoginRequest(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
