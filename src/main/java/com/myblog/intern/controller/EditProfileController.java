package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.request.EditProfileRequest;
import com.myblog.intern.service.EditProfileService;
import com.myblog.intern.service.JwtService;
import com.myblog.intern.service.UserDetailsServiceImpl;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/profile")
public class EditProfileController {
    @Autowired
    private EditProfileService editProfileService;
    @Autowired
    private UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public EditProfileController(EditProfileService editProfileService) {
        this.editProfileService = editProfileService;
    }

    @GetMapping(value = "/getByUsersId")
    public ResponseEntity<UserDetails> getUsersById(@RequestParam int id)  throws RuntimeException {
        UserDetails userDetails=null;
        try {
            userDetails=editProfileService.getUsersById(id);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDetails> (userDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<UserDetails> getUsersById(HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        UserDetails userDetails=null;
        try {
            userDetails=editProfileService.getUsersByUserId(userIdRequest);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDetails> (userDetails, HttpStatus.OK);
    }
    @GetMapping(value = "/getAllUserDetails")
    public ResponseEntity<List<UserDetails>> getAllUserProfiles()  throws RuntimeException {
        List<UserDetails> userDetails=null;
        try {
            userDetails=editProfileService.getAll();

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (userDetails, HttpStatus.OK);
    }

    @PostMapping("/Update")
    public ResponseEntity<String>addOrUpdate(@RequestBody EditProfileRequest editProfileRequest, HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
       // userDetails.setUserId(userIdRequest);
        UserDetails userDetails= editProfileService.getUserDetailsByUserID(userIdRequest);
        UserDetails newUserDetails= editProfileService.mapUserDetails(editProfileRequest, userDetails);
        try {
            /*if(editProfileService.saveUpdateProfile(newUserDetails)!=null){
                Optional<User> user=userService.findById(userIdRequest);
                user.get().setEmail(userDetails.getEmail());
                userService.save(user.get());
            }else  return new ResponseEntity<String> ("Error occur", HttpStatus.INTERNAL_SERVER_ERROR);
             */
            editProfileService.saveUpdateProfile(newUserDetails);
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Updated User Profile", HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<String>deleteById(@RequestBody UserDetails userDetails,HttpServletRequest request)  throws RuntimeException {
        String username= jwtService.extractUserName(jwtService.parseToken(request));
        Integer userIdRequest= userService.getUserIdByUserName(username);
        if(userIdRequest!=userDetails.getUserId())  return new ResponseEntity<String> ("Not a valid user", HttpStatus.OK);
        try {
            editProfileService.DeleteProfile(userDetails,request);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully deleted User Profile", HttpStatus.OK);
    }


}
