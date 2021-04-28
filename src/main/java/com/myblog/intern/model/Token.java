package com.myblog.intern.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name= "forgetpassurl")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="username")
    private String userName;
    @Column(name="token")
    private String token;
    @Column(name="generatedtime")
    private Timestamp generatedAt;
    @Column(name="expiredtime")
    private Timestamp expireAt;

    public Token(){

    }
    public Token(String userName, String token){
        this.userName=userName;
        this.token=token;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Timestamp getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }
    public boolean isExpire(){
        if(getExpireAt().compareTo(Timestamp.valueOf(LocalDateTime.now()))<0) return true;
        return false;
    }
}
