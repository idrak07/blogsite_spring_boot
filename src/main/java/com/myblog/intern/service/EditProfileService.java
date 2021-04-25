package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;


public interface EditProfileService {
    public UserDetails getUsersById(int userId);
    public UserDetails saveUpdateProfile(UserDetails userDetails);
    public boolean DeleteProfile(UserDetails userDetails);
}
