package com.myblog.intern.service;

import com.myblog.intern.model.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface EditProfileService {
    public UserDetails getUsersById(int id);
    public UserDetails getUsersByUserId(int userId);
    public UserDetails saveUpdateProfile(UserDetails userDetails);
    public boolean DeleteProfile(UserDetails userDetails, HttpServletRequest request);

    List<UserDetails> getAllByRole(String role);
    UserDetails getUserDetailsByUserID(int id);
    List<UserDetails>getAll();
}
