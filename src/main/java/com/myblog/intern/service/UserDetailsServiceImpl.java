package com.myblog.intern.service;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetailsImpl;
import com.myblog.intern.repository.UserRepository;
import com.myblog.intern.request.EditProfileRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+username));
        return user.map(UserDetailsImpl::new).get();
    }
}
