package com.myblog.intern.controller;

import com.myblog.intern.model.UserDetails;
import com.myblog.intern.service.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class EditProfileController {
    @Autowired
    private EditProfileService editProfileService;

    public EditProfileController(EditProfileService editProfileService) {
        this.editProfileService = editProfileService;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<UserDetails> getUsersById(@RequestParam int id)  throws RuntimeException {
        UserDetails userDetails=null;
        try {
            userDetails=editProfileService.getUsersById(id);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<UserDetails> (userDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDetails> (userDetails, HttpStatus.OK);
    }

    @PostMapping("/Update")
    public ResponseEntity<String>addOrUpdate(@RequestBody UserDetails userDetails)  throws RuntimeException {
        try {
            editProfileService.saveUpdateProfile(userDetails);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Updated User Profile", HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<String>deleteById(@RequestBody UserDetails userDetails)  throws RuntimeException {
        try {
            editProfileService.DeleteProfile(userDetails);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully deleted User Profile", HttpStatus.OK);
    }


}
