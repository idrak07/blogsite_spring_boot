package com.myblog.intern.controller;

import com.myblog.intern.model.User;
import com.myblog.intern.request.AuthenticationRequest;
import com.myblog.intern.response.AuthenticationResponse;
import com.myblog.intern.service.JwtService;
import com.myblog.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Optional<User> user= userService.fetchByCredential(authenticationRequest.getCredential());
        if(!user.isPresent()) return ResponseEntity.ok("Incorrect credential or password!");
        try {
            UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(user.get().getUserName(), authenticationRequest.getPassword());
            authenticationManager.authenticate(
                    token);
        }
        catch (Exception e) {
            //throw new Exception("Incorrect credential or password!", e);
            return ResponseEntity.unprocessableEntity().body("Incorrect credential or password!");
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.get().getUserName());

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/get-username")
    public String getUsername(HttpServletRequest request){
        return "token: "+jwtService.parseToken(request)+"\nusername: "+jwtService.extractUserName(jwtService.parseToken(request));
    }
}
