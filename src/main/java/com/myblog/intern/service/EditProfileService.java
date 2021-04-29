package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;

import java.util.List;


public interface EditProfileService {
    public UserDetails getUsersById(int userId);
    public UserDetails saveUpdateProfile(UserDetails userDetails);
    public boolean DeleteProfile(UserDetails userDetails);

    List<UserDetails> getAllByRole(String role);
    UserDetails getUserDetailsByUserID(int id);
}
