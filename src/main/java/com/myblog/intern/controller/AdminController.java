package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserEditProfileRepository;
import com.myblog.intern.repository.UserRepository;
import com.myblog.intern.request.AdminProfileRequest;
import com.myblog.intern.request.AdminSignUpRequest;
import com.myblog.intern.service.EditProfileService;
import com.myblog.intern.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EditProfileService editProfileService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEditProfileRepository userEditProfileRepository;


    private Validation validation=new Validation();

    public boolean  userNameExist(String username){
        Optional<User> user= Optional.ofNullable(userRepository.findByUserName(username));
        if(!user.isPresent()) return false;
        return true;
    }
    public boolean emailExist(String email){
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(email));
        if(!user.isPresent()) return false;
        return true;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<AdminProfileRequest> getUsersById(@RequestParam int id)  throws RuntimeException {
        UserDetails userDetails=null;
        User user=null;
        AdminProfileRequest adminProfileRequest=new AdminProfileRequest();
        try {
            userDetails=editProfileService.getUserDetailsByUserID(id);
            adminProfileRequest.setId(userDetails.getId());
            adminProfileRequest.setUserId(userDetails.getUserId());
            adminProfileRequest.setProfilePic(userDetails.getProfilePic());
            adminProfileRequest.setContact(userDetails.getContact());
            adminProfileRequest.setState(userDetails.getState());
            adminProfileRequest.setStreet(userDetails.getStreet());
            adminProfileRequest.setCountry(userDetails.getCountry());
            adminProfileRequest.setEmail(userDetails.getEmail());
            adminProfileRequest.setBirthMonth(userDetails.getBirthMonth());
            adminProfileRequest.setBirthDate(userDetails.getBirthDate());
            adminProfileRequest.setBirthYear(userDetails.getBirthYear());
            adminProfileRequest.setFirstName(userDetails.getFirstName());
            adminProfileRequest.setZipCode(userDetails.getZipCode());
            adminProfileRequest.setLastName(userDetails.getLastName());
            user=userRepository.findById(id).get();
            adminProfileRequest.setUserName(user.getUserName());
            adminProfileRequest.setRole(user.getRole());

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<AdminProfileRequest> (adminProfileRequest, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AdminProfileRequest> (adminProfileRequest, HttpStatus.OK);
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<List<AdminProfileRequest>> getAllReport()  throws RuntimeException {
        ArrayList<AdminProfileRequest> adminProfileRequests=new ArrayList<>();
        try {
            List<UserDetails> userDetails=editProfileService.getAllByRole("ROLE_admin");
            List<User>users=userRepository.findAllByRole("ROLE_admin");
             for(int i=0;i<userDetails.size();i++){
                 AdminProfileRequest adminProfileRequest=new AdminProfileRequest();
                 adminProfileRequest.setId(userDetails.get(i).getId());
                 adminProfileRequest.setUserId(userDetails.get(i).getUserId());
                 adminProfileRequest.setProfilePic(userDetails.get(i).getProfilePic());
                adminProfileRequest.setContact(userDetails.get(i).getContact());
                adminProfileRequest.setState(userDetails.get(i).getState());
                adminProfileRequest.setStreet(userDetails.get(i).getStreet());
                adminProfileRequest.setCountry(userDetails.get(i).getCountry());
                adminProfileRequest.setEmail(userDetails.get(i).getEmail());
                adminProfileRequest.setBirthMonth(userDetails.get(i).getBirthMonth());
                adminProfileRequest.setBirthDate(userDetails.get(i).getBirthDate());
                adminProfileRequest.setBirthYear(userDetails.get(i).getBirthYear());
                adminProfileRequest.setFirstName(userDetails.get(i).getFirstName());
                adminProfileRequest.setZipCode(userDetails.get(i).getZipCode());
                adminProfileRequest.setRole(userDetails.get(i).getRole());
                adminProfileRequest.setLastName(userDetails.get(i).getLastName());
                adminProfileRequests.add(adminProfileRequest);
            }
            for(int i=0;i<users.size();i++){
                adminProfileRequests.get(i).setUserName(users.get(i).getUserName());
            }


        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity (adminProfileRequests, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AdminProfileRequest>>((List<AdminProfileRequest>) adminProfileRequests, HttpStatus.OK);
    }

    @PostMapping("/UpdateAdminProfile")
    public ResponseEntity<String> adminUpdate(@RequestBody AdminProfileRequest adminProfileRequest)  throws RuntimeException {
        try {
            User user=new User();
            user=userRepository.findById(adminProfileRequest.getUserId()).get();
            user.setUserId(adminProfileRequest.getUserId());
            user.setUserName(adminProfileRequest.getUserName());
            user.setEmail(adminProfileRequest.getEmail());
            user.setRole("ROLE_admin");
            user.setActive(true);
            User p=userRepository.save(user);
            if(p!=null){
                UserDetails u=new UserDetails();
                u.setId(adminProfileRequest.getId());
                u.setUserId(adminProfileRequest.getUserId());
                u.setFirstName(adminProfileRequest.getFirstName());
                u.setLastName(adminProfileRequest.getLastName());
                u.setContact(adminProfileRequest.getContact());
                u.setProfilePic(adminProfileRequest.getProfilePic());
                u.setBirthYear(adminProfileRequest.getBirthYear());
                u.setBirthDate(adminProfileRequest.getBirthDate());
                u.setBirthMonth(adminProfileRequest.getBirthMonth());
                u.setState(adminProfileRequest.getState());
                u.setCountry(adminProfileRequest.getCountry());
                u.setStreet(adminProfileRequest.getStreet());
                u.setZipCode(adminProfileRequest.getZipCode());
                u.setEmail(adminProfileRequest.getEmail());
                u.setRole("ROLE_admin");
                userEditProfileRepository.save(u);
            }
        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully Updated User Profile", HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody AdminSignUpRequest adminSignUpRequest)  throws RuntimeException {
        try {
            if(adminSignUpRequest.getUserName().length()==0)   return new ResponseEntity<String> ("UserName Can't be Empty", HttpStatus.NOT_FOUND);
            if(adminSignUpRequest.getFirstName().length()==0 || adminSignUpRequest.getLastName().length()==0)   return new ResponseEntity<String> ("Name can not be empty! ", HttpStatus.NOT_FOUND);
            if(adminSignUpRequest.getPassword().length()<8)  return new ResponseEntity<String> ("Password should contain at least 8 characters!", HttpStatus.NOT_FOUND);
            if(!(validation.isValidEmailPattern(adminSignUpRequest.getEmail())))  return new ResponseEntity<String> ("Invalid email!", HttpStatus.NOT_FOUND);
           // if(!(validation.isValidUserNamePattern(adminSignUpRequest.getUserName()))) return new ResponseEntity<String> ("Invalid username!!", HttpStatus.NOT_FOUND);
            if(userNameExist(adminSignUpRequest.getUserName())) return new ResponseEntity<String> ("username is taken!", HttpStatus.NOT_FOUND);
            if(emailExist(adminSignUpRequest.getEmail())) return new ResponseEntity<String> ("Email already exists!", HttpStatus.NOT_FOUND);
            User user=new User();
            user.setPassword(passwordEncoder.encode(adminSignUpRequest.getPassword()));
            user.setUserName(adminSignUpRequest.getUserName());
            user.setEmail(adminSignUpRequest.getEmail());
            user.setActive(true);
            user.setRole("ROLE_admin");
            userRepository.save(user);
            UserDetails userDetails=new UserDetails();
            userDetails.setUserId(userRepository.findByUserName(adminSignUpRequest.getUserName()).getUserId());
            userDetails.setFirstName(adminSignUpRequest.getFirstName());
            userDetails.setLastName(adminSignUpRequest.getLastName());
            userDetails.setRole("ROLE_admin");
            userDetails.setEmail(adminSignUpRequest.getEmail());
            userDetails.setCountry(adminSignUpRequest.getCountry());
            editProfileService.saveUpdateProfile(userDetails);

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully added Admin ", HttpStatus.OK);
    }

    @PostMapping("/deleteAdmin")
    public ResponseEntity<String>deleteById(@RequestBody AdminProfileRequest adminProfileRequest)  throws RuntimeException {
        try {
            adminProfileRequest.setUserId(userRepository.findByUserName(adminProfileRequest.getUserName()).getUserId());
            userEditProfileRepository.deleteById(adminProfileRequest.getUserId());
            userRepository.deleteById(userRepository.findByUserName(adminProfileRequest.getUserName()).getUserId());

        }catch (Exception ex){
            ex.getMessage();
            return new ResponseEntity<String> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String> ("Successfully deleted admin", HttpStatus.OK);
    }
}
