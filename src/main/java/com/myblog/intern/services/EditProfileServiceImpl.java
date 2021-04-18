package com.myblog.intern.services;

import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserEditProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditProfileServiceImpl implements EditProfileService{
    @Autowired
    private UserEditProfileRepository userEditProfileRepository;

    @Override
    public UserDetails getUsersById(int userId) {
        return userEditProfileRepository.findById(userId).orElse(null);
    }

  @Override
    public UserDetails saveUpdateProfile(UserDetails userDetails) {
      UserDetails u=  userEditProfileRepository.save(userDetails);
      int p=userEditProfileRepository.updateUserDetailInteger(userDetails.getFirstName()+userDetails.getLastName(),userDetails.getEmail());
      return userDetails;
      }


}
