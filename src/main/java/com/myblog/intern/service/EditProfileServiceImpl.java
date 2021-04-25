package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserEditProfileRepository;
import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EditProfileServiceImpl implements EditProfileService{
    @Autowired
    private UserEditProfileRepository userEditProfileRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails getUsersById(int id) {
       return userEditProfileRepository.findById(id).orElse(null);

    }

  @Override
    public UserDetails saveUpdateProfile(UserDetails userDetails) {

      Optional<User> user=userRepository.findById(userDetails.getUserId());
      user.get().setRole(userDetails.getRole());
      user.get().setEmail(userDetails.getEmail());
      userRepository.save(user.get());
      UserDetails u=  userEditProfileRepository.save(userDetails);
      return userDetails;
      }

    @Override
    public boolean DeleteProfile(UserDetails userDetails) {
        userEditProfileRepository.deleteById(userDetails.getUserId());
        return true;
    }


}
