package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Integer getUserIdByUserName(String username){
        Integer userId=-1;
        Optional<User> user= userRepository.findUserByUserName(username);
        if(user.isPresent()) userId=user.get().getUserId();
        /*try{
            userId=userRepository.findUserByUserName(username).getUserId();
        }
        catch (Exception e){
            System.out.println("Service: UserService, Method: getUserIdByUserName, Error:"+e.getMessage());
        }*/
        return userId;
    }
    public boolean userExists(Integer userid){
        boolean flag=false;
        try{
            if (userRepository.existsById(userid)){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Service: UserService, Method: getUserIdByUserName, Error:"+e.getMessage());
        }
        return flag;
    }

}
