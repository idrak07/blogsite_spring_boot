package com.myblog.intern.service;


import com.myblog.intern.model.User;
import com.myblog.intern.repository.ChangePasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    @Autowired
    private ChangePasswordRepository changePasswordRepository;
    public User getByPassword(String password) {
        return changePasswordRepository.getByPassword(password);
    }

    public User saveUpdateProfile(User user) {
        User u=  changePasswordRepository.save(user);
        return u;
    }
}
