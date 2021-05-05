package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserEditProfileRepository;
import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Service
public class EditProfileServiceImpl implements EditProfileService{
    @Autowired
    private UserEditProfileRepository userEditProfileRepository;



    @Override
    public UserDetails getUsersById(int id) {
       return userEditProfileRepository.findById(id).orElse(null);

    }

    @Override
    public UserDetails getUsersByUserId(int userId) {
        return userEditProfileRepository.findByUserId(userId);
    }

    @Override
    public UserDetails saveUpdateProfile(UserDetails userDetails) {
      UserDetails u=  userEditProfileRepository.save(userDetails);
      return u;
      }

    @Override
    public boolean DeleteProfile(UserDetails userDetails, HttpServletRequest request) {
        userEditProfileRepository.deleteById(userDetails.getUserId());
        return true;
    }
@Override
    public List<UserDetails> getAllByRole(String role){
        List<UserDetails> u=userEditProfileRepository.findAllByRole("ROLE_admin");
        return u;
    }

    @Override
    public UserDetails getUserDetailsByUserID(int id) {
        UserDetails u=userEditProfileRepository.findByUserId(id);
        return  u;
    }

    @Override
    public List<UserDetails> getAll() {
        List<UserDetails>userDetails=userEditProfileRepository.findAll();
        return  userDetails;
    }


}
