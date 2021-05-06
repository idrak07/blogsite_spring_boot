package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserEditProfileRepository;
import com.myblog.intern.repository.UserRepository;
import com.myblog.intern.request.EditProfileRequest;
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

    public com.myblog.intern.model.UserDetails mapUserDetails(EditProfileRequest editProfileRequest, com.myblog.intern.model.UserDetails userDetails){
        userDetails.setFirstName(editProfileRequest.getFirstName());
        userDetails.setLastName(editProfileRequest.getLastName());
        userDetails.setBirthDate(editProfileRequest.getBirthDate());
        userDetails.setBirthMonth(editProfileRequest.getBirthMonth());
        userDetails.setBirthYear(editProfileRequest.getBirthYear());
        userDetails.setContact(editProfileRequest.getContact());
        userDetails.setCountry(editProfileRequest.getCountry());
        userDetails.setState(editProfileRequest.getState());
        userDetails.setStreet(editProfileRequest.getStreet());
        userDetails.setProfilePic(editProfileRequest.getProfilePic());
        userDetails.setZipCode(editProfileRequest.getZipCode());
        return userDetails;
    }
}
