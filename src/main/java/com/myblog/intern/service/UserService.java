package com.myblog.intern.service;

import com.myblog.intern.model.Post;
import com.myblog.intern.request.LoginRequest;
import com.myblog.intern.request.PasswordChangeRequest;
import com.myblog.intern.request.SignupRequest;
import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import com.myblog.intern.repository.UserDetailsRepository;
import com.myblog.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    public String Test(LoginRequest loginRequest){
        return loginRequest.getUsername();
    }
    public boolean userNameExist(String username){
        Optional<User> user= userRepository.findByUserName(username);
        if(!user.isPresent()) return false;
        return true;
    }
    public boolean emailExist(String email){
        Optional<User> user= userRepository.findByEmail(email);
        if(!user.isPresent()) return false;
        return true;
    }
    public List<User> fetchAllUsers() {
        List<User> userList=new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }
    public boolean isValidEmailPattern(String email){
        //String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        String regex="[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z][A-Za-z0-9]*)*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidUserNamePattern(String userName){
        String regex = "^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(userName);
        return matcher.matches();
    }
    public String encodePassword(String plainText){
        //return BCrypt.hashpw(plainText, BCrypt.gensalt());
        return passwordEncoder.encode(plainText);
    }

    public String addUser(SignupRequest signupRequest){
        if(signupRequest.getUserName().length()==0) return "username can not be empty!";
        if(signupRequest.getFirstName().length()==0 || signupRequest.getLastName().length()==0) return "Name can not be empty!";
        if(signupRequest.getPassword().length()<8) return "Password should contain at least 8 characters!";
        if(!isValidEmailPattern(signupRequest.getEmail())) return "Invalid email!";
        if(!isValidUserNamePattern(signupRequest.getUserName())) return "Invalid username!!";
        if(userNameExist(signupRequest.getUserName())) return "username is taken!";
        if(emailExist(signupRequest.getEmail())) return "Email already exists!";
        User user=new User(signupRequest.getUserName(), encodePassword(signupRequest.getPassword()), signupRequest.getEmail(), true, "ROLE_user");
        user.setActive(true);
        userRepository.save(user);
        UserDetails userDetails=new UserDetails(userRepository.findByUserName(signupRequest.getUserName()).get().getUserId(), "ROLE_user", signupRequest.getFirstName(), signupRequest.getLastName(), signupRequest.getEmail());
        userDetailsRepository.save(userDetails);
        return "Registration Successful!";
    }
    public Optional<User> fetchByCredential(String credential){
        Optional<User> user=userRepository.findByUserName(credential);
        if(!user.isPresent()) user= userRepository.findByEmail(credential);
        return user;
    }
    public String updatePassword(Integer userId, PasswordChangeRequest passwordChangeRequest){
        if(passwordChangeRequest.getNewPassword().length()<8) return "Password should contain at least 8 characters!";
        if(passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getConfirmNewPassword())){
            Optional<User> user=userRepository.findById(userId);
            User myUser=user.get();
            myUser.setPassword(encodePassword(passwordChangeRequest.getNewPassword()));
            userRepository.save(myUser);
            tokenService.deleteByUserName(user.get().getUserName());
            return "Password reset successful!";
        }
        return "Password did not match!";
    }
    public UserDetails getUserDetails(User user){
        return userDetailsRepository.findByUserId(user.getUserId());
    }

    public Integer getUserIdByUserName(String username){
        Integer userId=-1;
        Optional<User> user= userRepository.findByUserName(username);
        if(user.isPresent()) userId=user.get().getUserId();
        /*try{
            userId=userRepository.findUserByUserName(username).getUserId();
        }
        catch (Exception e){
            System.out.println("Service: UserService, Method: getUserIdByUserName, Error:"+e.getMessage());
        }*/
        return userId;
    }
    public boolean userExists(Integer userid){
        boolean flag=false;
        try{
            if (userRepository.existsById(userid)){
                flag=true;
            }
        }
        catch (Exception e){
            System.out.println("Service: UserService, Method: getUserIdByUserName, Error:"+e.getMessage());
        }
        return flag;
    }

}