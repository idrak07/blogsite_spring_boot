package com.myblog.intern.service;

import com.myblog.intern.model.LoginRequest;
import com.myblog.intern.model.User;
import com.myblog.intern.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public String Test(LoginRequest loginRequest){
        return loginRequest.getUsername();
    }
    public boolean userNameExist(String username){
        User user= null;
        user= userRepository.findByUserName(username);
        if(user==null) return false;
        return true;
    }
    public boolean emailExist(String email){
        User user= null;
        user= userRepository.findByEmail(email);
        if(user==null) return false;
        return true;
    }
    public List<User> fetchAllUsers() {
        List<User> userList=new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }
}
