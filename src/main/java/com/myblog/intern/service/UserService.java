package com.myblog.intern.service;

import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Integer getUserIdByUserName(String username){
        Integer userId=-1;
        try{
            userId=userRepository.findUserByUserName(username).getUserId();
        }
        catch (Exception e){
            System.out.println("Service: UserService, Method: getUserIdByUserName, Error:"+e.getMessage());
        }
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
